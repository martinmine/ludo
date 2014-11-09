package no.hig.imt3281.ludo.backend;

import java.util.HashMap;
import java.util.Map;

/**
 * Keep track of logged in users,
 * manages user registration/login,
 * request users by their userID,
 * notified when a user disconnects
 */
public class UserManager {
    private Map<Integer, User> activeUsers;

    /**
     * Prepares the user manager
     */
    public UserManager() {
        this.activeUsers = new HashMap<>();
    }

    /**
     * Gets a registered user
     * @param userID ID of the user
     * @return The user data/object
     */
    public User getUser(int userID) {
        return null;
    }

    /**
     * Gets a user
     * @param username Username of the user
     * @return The user data
     */
    public User getUser(String username) {
        return null;
    }

    /**
     * Gets a user
     * @param username Username of the user
     * @param password Hashed password
     * @return User object
     */
    public User getUser(String username, String password) {
        return null;
    }

    /**
     * Sets the state of the user to be signed in
     * @param user User that has successfully signed in
     */
    public void setLoggedIn(User user) {
        this.activeUsers.put(user.getId(), user);
    }

    /**
     * Sets the state in the server to logged out for the user
     * @param userId ID of the user that is signing out
     */
    public void reportLoggedOut(int userId) {
        this.activeUsers.remove(userId);
    }

    /**
     * Identifies wether a user has signed in or not to the server
     * @param user User that is signing in
     * @return True if the user is active/signed in, otherwise false
     */
    public boolean userIsActive(User user) {
        return this.activeUsers.containsKey(user.getId());
    }


    /*

    public static Integer addUser(User player) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            employeeID = (Integer) session.save(player);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }
     */
}
