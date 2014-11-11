package no.hig.imt3281.ludo.backend;

import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import no.hig.imt3281.ludo.messaging.Message;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Keep track of logged in users,
 * manages user registration/login,
 * request users by their userID,
 * notified when a user disconnects
 */
public class UserManager {
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
        if (user != null)
            return user;

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
            User user = (User)criteria.uniqueResult();

            User loadedUser;
            if ((loadedUser = this.activeUsers.get(user.getId())) != null) {
                return loadedUser;
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
    public User getUser(String username, String password) /*throws UnsupportedEncodingException, NoSuchAlgorithmException*/ {
        Session session = ServerEnvironment.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            /*String hashedPassword = hashPassword(password);
            System.out.println("Password " + hashedPassword);*/
            criteria.add(Restrictions.eq("username", username));
            criteria.add(Restrictions.eq("password", password));

            Object result = criteria.uniqueResult();
            if (result != null) {
                User user = (User)result;
                User loadedUser;
                if ((loadedUser = this.activeUsers.get(user.getId())) != null) {
                    return loadedUser;
                }
                else {
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
     */
    public void setLoggedIn(User user) {
        if (this.activeUsers.containsKey(user.getId())) {
            reportLoggedOut(user.getId());
        }

        this.activeUsers.addItem(user.getId(), user);
    }

    /**
     * Sets the state in the server to logged out for the user
     * @param userId ID of the user that is signing out
     */
    public void reportLoggedOut(int userId) {
        this.activeUsers.removeItem(userId);
        // TODO: Notify active games, chats, etc.
    }

    /**
     * Identifies whether a user has signed in or not to the server
     * @param user User that is signing in
     * @return True if the user is active/signed in, otherwise false
     */
    public boolean userIsActive(User user) {
        return this.activeUsers.containsKey(user.getId());
    }

    public void registerUser(User user) throws Exception {
        Session session = ServerEnvironment.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            int userID = (Integer) session.save(user);
            user.setId(userID);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
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
                    user.getClientConnection().close();
                }
            }
        });
    }

    public void onCycle() {
        this.activeUsers.onCycle();
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String text = password + ServerEnvironment.getPasswordSalt();
        byte[] hash = digest.digest(text.getBytes("UTF-8"));

        return new String(hash);
    }
}
