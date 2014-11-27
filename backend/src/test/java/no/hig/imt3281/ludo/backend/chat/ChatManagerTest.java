package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.game.Game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the chat manager.
 */
public class ChatManagerTest {
    private ChatManager chatManager;
    private static final String SAMPLE_TEXT = "foo";

    @Before
    public void setUp() {
        ServerEnvironment.initialize();
        chatManager = ServerEnvironment.getChatManager();
    }

    @Test
    public void testChatRoomExists() throws Exception {
        GroupChat room = chatManager.createGroupChat(SAMPLE_TEXT);
        chatManager.onCycle();
        assertTrue(chatManager.chatRoomExists(room.getCaption()));
    }

    @Test
    public void testCreateGroupChat() throws Exception {
        GroupChat firstRoom = chatManager.createGroupChat(SAMPLE_TEXT);
        GroupChat secondRoom = chatManager.createGroupChat("bar");

        assertNotNull(firstRoom);
        assertNotNull(secondRoom);
        assertEquals(firstRoom.getId() + 1, secondRoom.getId());
        assertEquals(firstRoom.getCaption(), SAMPLE_TEXT);
    }

    @Test
    public void testCreateGameChat() throws Exception {
        Game game = ServerEnvironment.getGameManager().createGame();
        GameChat chat = chatManager.createGameChat(game);
        assertNotNull(chat);
        chatManager.onCycle();

        assertEquals(chat, chatManager.getGameChat(game.getGameId()));
    }

    @Test
    public void testStoreChatLogEntry() throws Exception {
        ChatLogEntry entry = new ChatLogEntry(ChatLogEntry.PUBLIC_MESSAGE);
        entry.setMessage(SAMPLE_TEXT);
        chatManager.storeChatLogEntry(entry);

        assertTrue(entry.getId() > 0);
    }
}