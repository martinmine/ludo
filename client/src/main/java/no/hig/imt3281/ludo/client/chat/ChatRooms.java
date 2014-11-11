package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.gui.chat.ChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GlobalChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GroupChatChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joakim on 11.11.2014.
 */
public class ChatRooms {
    private Map<Integer, ChatChannel> chatRooms;
    private ChatRooms() {
        this.chatRooms = new HashMap<>();

        this.chatRooms.put(-1, new GlobalChatChannel());
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
        chatRooms.put(id, new GroupChatChannel(name));
    }

    public void joinGameChannel(int gameId) {
        // Game channel is always -2
    }


}

