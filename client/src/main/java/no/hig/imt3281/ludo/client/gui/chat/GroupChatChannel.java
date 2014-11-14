package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;
import no.hig.imt3281.ludo.client.gui.GuiManager;

/**
 * Created by Joakim on 05.11.2014.
 */
public class GroupChatChannel extends ChatChannel {
    public GroupChatChannel(int id, String channelName) {
        super(id, channelName,
                Main.resourceBundle.getString("USERGENERATED_CHAT_CHANNEL_WELCOME_MSG"),
                "/img/world20.gif", ChatMessageHandler.GROUP_CHAT);
        GuiManager.getChatPanel().joinNewChatroom(this);
    }
}
