package no.hig.imt3281.ludo.backend.test;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.UserManager;
import org.hibernate.HibernateException;
import org.junit.Test;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Tests core functionality in the user manager
 */
public class UserManagerTest {
    private static final Logger LOGGER = Logger.getLogger(UserManagerTest.class.getSimpleName());
    private static UserManager userManager;


    private UserManager getUserManager() {
        if (userManager == null) {
            ServerEnvironment.initialize();
            userManager = ServerEnvironment.getUserManager();
        }

        assertNotNull(userManager);

        return userManager;
    }

    @Test
    public void testGetUserByUserId() {
        User user = getUserManager().getUser(1);
        assertNotNull(user);
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User("test", "test@example.com");
        try {
            getUserManager().registerUser(user);
        } catch (HibernateException ex) {
            LOGGER.info("User test already exists:");
            LOGGER.log(Level.INFO, ex.getMessage(), ex);
        }

        user = getUserManager().getUser("test");
        assertNotNull(user);
    }

    @Test
    public void testRegisterUser() {
        User user = new User("test" + new Random().nextInt(), "test@example.com");
        user.setPassword("password");
        try {
            getUserManager().registerUser(user);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            user = null;
        }

        assertTrue(user != null && user.getId() > 0);

        User gen1 = new User("u", "p");
        User gen2 = new User("u", "p");

        Exception ex = null;

        try {
            getUserManager().registerUser(gen1);
            getUserManager().registerUser(gen2);
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(gen2.getId() == 0);
    }
}