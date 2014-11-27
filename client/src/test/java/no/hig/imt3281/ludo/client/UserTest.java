package no.hig.imt3281.ludo.client;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private static final int TEST_NUMBER = 5;

    @Test
    public void testGetInstance() {
        assertNotEquals(null, User.getInstance());
    }

    @Test
    public void testInitialize() {
        User.getInstance().initialize(TEST_NUMBER);
        assertEquals(TEST_NUMBER, User.getInstance().getUserId());
    }
}