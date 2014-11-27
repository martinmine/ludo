package no.hig.imt3281.ludo.client;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    int testnumber = 5;

    @Test
    public void testGetInstance() throws Exception {
        assertNotEquals(null, User.getInstance());
    }

    @Test
    public void testInitialize() throws Exception {
        User.getInstance().initialize(testnumber);
        assertEquals(testnumber, User.getInstance().getUserId());
    }
}