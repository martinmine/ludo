package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.gui.chat.ChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GameChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GlobalChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GroupChatChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * ChatRooms keeps a hashtable representation of all the chatrooms.
 * Used for keeping track of all the chatrooms.
 */
public class ChatRooms {
    public static final int GLOBAL_CHAT_KEY = -1;
    public static final int GAME_CHAT_KEY = -2;
    private Map<Integer, ChatChannel> rooms;

    private ChatRooms() {
        this.rooms = new HashMap<>();
        this.rooms.put(GLOBAL_CHAT_KEY, new GlobalChatChannel());
    }

    /**
     * Nested singletonHolder class
     */
    private static class SingletonHolder {
        private SingletonHolder() {
        }

        private static final ChatRooms INSTANCE = new ChatRooms();
    }

    /**
     * @return returns the instance of ChatRooms
     */
    public static ChatRooms getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * @param id is the key in the chatroom hashmap
     * @return returns a ChatChannel with given id
     */
    public ChatChannel getChannel(int id) {
        return rooms.get(id);
    }

    /**
     * Join a user generated chatroom
     * @param id given from server response
     * @param name name is usergenerated but approved by server
     */
    public void joinGroupChannel(int id, String name) {
        rooms.put(id, new GroupChatChannel(id, name));
    }

    /**
     * Join a game chat when a new game is started
     * @param gameId id of a specific gameChatroom related
     * to a specific ongoing game
     */
    public void joinGameChannel(int gameId) {
        rooms.put(GAME_CHAT_KEY, new GameChatChannel(gameId));
    }
}

