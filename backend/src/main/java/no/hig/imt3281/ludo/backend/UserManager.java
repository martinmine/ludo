package no.hig.imt3281.ludo.backend;

import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import no.hig.imt3281.ludo.backend.game.Game;
import no.hig.imt3281.ludo.messaging.ChallengeableUser;
import no.hig.imt3281.ludo.messaging.Message;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Keep track of logged in users,
 * manages user registration/login,
 * request users by their userID,
 * notified when a user disconnects
 */
public class UserManager {
    private static final Logger LOGGER = Logger.getLogger(UserManager.class.getSimpleName());
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final int BYTE_MULTIPLIER = 2;
    private static final int BIT_SHIFT_LENGTH = 4;

    private QueuedMap<Integer, User> activeUsers;

    /**
     * Prepares the user manager
     */
    public UserManager() {
        this.activeUsers = new QueuedMap<>(new HashMap<>());
    }

    /**
     * Gets a registered user
     * @param userID ID of the user
     * @return The user data/object
     */
    public User getUser(int userID) {
        User user = activeUsers.get(userID);
        if (user != null) {
            return user;
        }

        Session session = ServerEnvironment.getSessionFactory().openSession();

        try {
            return (User)session.load(User.class, userID);
        } finally {
            session.close();
        }
    }

    /**
     * Gets a user
     * @param username Username of the user
     * @return The user data
     */
    public User getUser(String username) {
        Session session = ServerEnvironment.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));
            Object result = criteria.uniqueResult();
            User user = null;

            if (result != null && result instanceof User) {
                user = (User)result;
                User loadedUser;
                if ((loadedUser = this.activeUsers.get(user.getId())) != null) {
                    return loadedUser;
                }
            }

            return user;
        } finally {
            session.close();
        }
    }

    /**
     * Gets a user
     * @param username Username of the user
     * @param password Hashed password
     * @return User object
     */
    public User getUser(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Session session = ServerEnvironment.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            String hashedPassword = hashPassword(password);
            criteria.add(Restrictions.eq("username", username));
            criteria.add(Restrictions.eq("password", hashedPassword));

            Object result = criteria.uniqueResult();
            if (result != null && result instanceof User) {
                User user = (User)result;
                User loadedUser;
                if ((loadedUser = this.activeUsers.get(user.getId())) != null) {
                    return loadedUser;
                } else {
                    return user;
                }
            }

            return null;
        } finally {
            session.close();
        }
    }

    /**
     * Sets the state of the user to be signed in
     * @param user User that has successfully signed in
     * @param context The connection to the user who tries to sign in
     */
    public void setLoggedIn(User user, CommunicationContext context) {
        User activeUser = this.activeUsers.get(user.getId());
        if (activeUser != null && activeUser.getClientConnection() != null) {
            activeUser.getClientConnection().close();
        }

        this.activeUsers.addItem(user.getId(), user);

        context.setReferenceToken(user.getId());
        context.setStatusListener(user);
        user.setClientConnection(context);
    }

    /**
     * Sets the state in the server to logged out for the user
     * @param userId ID of the user that is signing out
     */
    public void reportLoggedOut(int userId) {
        LOGGER.info("User " + userId + " logging out");
        User user = this.activeUsers.get(userId);

        this.activeUsers.removeItem(userId);
        ServerEnvironment.getChatManager().removeFromChatRooms(userId);

        if (user != null && user.getCurrentGameId() != 0) {
            Game currentUserGame = ServerEnvironment.getGameManager().getGame(user.getCurrentGameId());
            if (currentUserGame != null) {
                currentUserGame.leave(user);
            }
        }
    }

    /**
     * Identifies whether a user has signed in or not to the server
     * @param user User that is signing in
     * @return True if the user is active/signed in, otherwise false
     */
    public boolean userIsActive(User user) {
        return this.activeUsers.containsKey(user.getId());
    }

    /**
     * Registers a user in the database.
     * @param user The user to register.
     * @throws HibernateException Database error.
     */
    public void registerUser(User user) throws HibernateException {
        Session session = ServerEnvironment.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            int userID = (Integer) session.save(user);
            user.setId(userID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * Broadcasts a message to all signed in users
     * @param message Message to be sent
     */
    public void broadcastMessage(final Message message) {
        this.activeUsers.requestForeach((Integer userId, User user) -> {
            if (user.getClientConnection() != null) {
                try {
                    user.getClientConnection().sendMessage(message);
                } catch (IOException e) {
                    user.getClientConnection().close(e);
                }
            }
        });
    }

    /**
     * Cycles the user manager and its internal resources.
     */
    public void onCycle() {
        this.activeUsers.onCycle();
    }

    /**
     * Hashes a password according with a salt and SHA-256 algorithm.
     * @param password Password to hash.
     * @return Hashed password.
     * @throws NoSuchAlgorithmException SHA-256 algorithm not available.
     * @throws UnsupportedEncodingException UTF-8 is not available.
     */
    public String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String text = password + ServerEnvironment.getPasswordSalt();
        byte[] hash = digest.digest(text.getBytes("UTF-8"));
        return bytesToHex(hash);
    }

    // code from http://stackoverflow.com/a/9855338/1924825
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * BYTE_MULTIPLIER];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * BYTE_MULTIPLIER] = HEX_ARRAY[v >>> BIT_SHIFT_LENGTH];
            hexChars[j * BYTE_MULTIPLIER + 1] = HEX_ARRAY[v & 0x0F];
        }

        return new String(hexChars);
    }

    /**
     * Requests that a user should be sent a list of all the signed on users.
     * @param requestingUser User that should receive the list.
     */
    public void requestUserList(final User requestingUser) {
        final ChallengeableUser response = new ChallengeableUser();
        this.activeUsers.requestForeach((userId, user) -> {
            response.setUserId(userId);
            response.setUsername(user.getUsername());

            if (requestingUser.getClientConnection() != null) {
                try {
                    requestingUser.getClientConnection().sendMessage(response);
                } catch (IOException e) {
                    requestingUser.getClientConnection().close(e);
                }
            }
        });
    }
}
