package no.hig.imt3281.ludo.client.chat;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChatMessageHandlerTest {
    private ChatMessageHandler cmh;

    @Test
    public void testGetInstance() throws Exception {
        cmh = ChatMessageHandler.getInstance();
        assertNotEquals(null, cmh);
    }

    @Test
    public void testSetCurrentState() throws Exception {
        cmh = ChatMessageHandler.getInstance();
        assertEquals(cmh.getCurrentState(), ChatMessageHandler.GLOBAL_CHAT);
        cmh.setCurrentState(ChatMessageHandler.GAME_CHAT);
        assertEquals(cmh.getCurrentState(), ChatMessageHandler.GAME_CHAT);
        cmh.setCurrentState(ChatMessageHandler.GROUP_CHAT);
        assertEquals(cmh.getCurrentState(), ChatMessageHandler.GROUP_CHAT);
    }
}