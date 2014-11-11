package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.Main;

/**
 * Created by Joakim on 05.11.2014.
 */
public class GroupChatChannel extends ChatChannel {
    public GroupChatChannel() {
        super(Main.resourceBundle.getString("GROUP_CHAT_CHANNEL_WELCOME_MSG"));
    }
}
