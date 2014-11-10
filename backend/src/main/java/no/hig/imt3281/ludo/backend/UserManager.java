package no.hig.imt3281.ludo.backend;

import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.HashMap;
import java.util.Map;

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
            return (User)criteria.uniqueResult();
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
    public User getUser(String username, String password) {
        Session session = ServerEnvironment.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username));
            criteria.add(Restrictions.eq("password", password));
            return (User)criteria.uniqueResult();
        } finally {
            session.close();
        }
    }

    /**
     * Sets the state of the user to be signed in
     * @param user User that has successfully signed in
     */
    public void setLoggedIn(User user) {
        // TODO: Thread safety
        this.activeUsers.addItem(user.getId(), user);
    }

    /**
     * Sets the state in the server to logged out for the user
     * @param userId ID of the user that is signing out
     */
    public void reportLoggedOut(int userId) {
        // TODO: thread safety
        this.activeUsers.removeItem(userId);
    }

    /**
     * Identifies wether a user has signed in or not to the server
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
}
