package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.chat.ChatChannel;
import no.hig.imt3281.ludo.client.gui.chat.GlobalChatChannel;

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

    public void joinGroupChannel() {

    }

    public void joinGameChannel() {
        // Game channel is always -2
    }


}

