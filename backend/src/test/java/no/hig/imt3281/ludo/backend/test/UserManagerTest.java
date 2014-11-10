package no.hig.imt3281.ludo.backend.test;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.UserManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class UserManagerTest {
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
    public void testGetUserByUserId() throws Exception {
        User user = getUserManager().getUser(1);
        assertNotNull(user);
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        User user = getUserManager().getUser("test");
        assertNotNull(user);
    }

    @Test
    public void testGetUserByPassword() throws Exception {
        User user = getUserManager().getUser("test", "password");
        assertNotNull(user);
    }

    @Test
    public void testReportLoggedOut() throws Exception {

    }

    @Test
    public void testUserIsActive() throws Exception {

    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User("test" + new Random().nextInt(), "test@example.com");
        user.setPassword("password");
        getUserManager().registerUser(user);

        assertTrue(user.getId() > 0);


        User gen1 = new User("u", "p");
        User gen2 = new User("u", "p");

        Exception ex = null;

        try {
            getUserManager().registerUser(gen1);
            getUserManager().registerUser(gen2);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue(gen2.getId() == 0);

    }
}