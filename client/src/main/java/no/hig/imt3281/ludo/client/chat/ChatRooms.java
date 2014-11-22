package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.gui.chat.ChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GameChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GlobalChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GroupChatChannel;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * ChatRooms keeps a hashtable representation of all the chatrooms.
 * Used for keeping track of all the chatrooms.
 *
 * Singleton
 */
public class ChatRooms {
    public static int GLOBAL_CHAT_KEY = -1;
    public static int GAME_CHAT_KEY = -2;
    private Map<Integer, ChatChannel> chatRooms;
    private ChatRooms() {
        this.chatRooms = new HashMap<>();
        this.chatRooms.put(GLOBAL_CHAT_KEY, new GlobalChatChannel());
    }

    /**
     * Nested singletonHolder class
     */
    private static class SingletonHolder {
        private static final ChatRooms INSTANCE = new ChatRooms();
    }

    /**
     *
     * @return returns the instance of ChatRooms
     */
    public static ChatRooms getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     *
     * @param id is the key in the chatroom hashmap
     * @return returns a ChatChannel with given id
     */
    public ChatChannel getChannel(int id) {
        return chatRooms.get(id);
    }

    /**
     * Join a user generated chatroom
     * @param id given from server response
     * @param name name is usergenerated but approved by server
     */
    public void joinGroupChannel(int id, String name) {
        chatRooms.put(id, new GroupChatChannel(id,name));
    }

    /**
     *
     * @param gameId id of a specific gameChatroom related
     * to a specific ongoing game
     */
    public void joinGameChannel(int gameId) {
        chatRooms.put(GAME_CHAT_KEY, new GameChatChannel(gameId));
    }


}

