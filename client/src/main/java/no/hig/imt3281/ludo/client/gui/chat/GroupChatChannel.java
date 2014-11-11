package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.Main;

/**
 * Created by Joakim on 05.11.2014.
 */
public class GroupChatChannel extends ChatChannel {
    public GroupChatChannel(String channelName) {
        super(channelName,
                Main.resourceBundle.getString("USERGENERATED_CHAT_CHANNEL_WELCOME_MSG"),
                "/img/world20.gif");
    }
}
