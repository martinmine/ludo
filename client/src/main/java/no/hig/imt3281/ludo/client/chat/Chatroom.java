package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.gui.chat.ChatChannel;

/**
 * Created by Joakim on 11.11.2014.
 */
public class ChatRoom {
    private int roomId;
    private ChatChannel channel;

    public ChatRoom(int id, ChatChannel channel) {
        this.roomId = id;
        this.channel = channel;
    }

}
