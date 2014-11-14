package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.gui.chat.ChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GameChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GlobalChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GroupChatChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joakim on 11.11.2014.
 */
public class ChatRooms {
    public static int GLOBAL_CHAT_KEY = -1;
    public static int GAME_CHAT_KEY = -2;
    private Map<Integer, ChatChannel> chatRooms;
    private ChatRooms() {
        this.chatRooms = new HashMap<>();
        this.chatRooms.put(GLOBAL_CHAT_KEY, new GlobalChatChannel());
    }

    private static class SingletonHolder {
        private static final ChatRooms INSTANCE = new ChatRooms();
    }

    public static ChatRooms getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ChatChannel getChannel(int id) {
        return chatRooms.get(id);
    }

    public void joinGroupChannel(int id, String name) {
        chatRooms.put(id, new GroupChatChannel(id,name));
    }

    public void joinGameChannel(int gameId) {
        chatRooms.put(GAME_CHAT_KEY, new GameChatChannel(gameId));
    }


}

