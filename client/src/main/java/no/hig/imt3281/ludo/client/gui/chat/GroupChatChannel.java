package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;
import no.hig.imt3281.ludo.client.gui.GuiManager;

/**
 * GroupChatChannel is a user generated chat channel
 *
 */
public class GroupChatChannel extends ChatChannel {
    public GroupChatChannel(int id, String channelName) {
        super(id, channelName,
                Main.resourceBundle.getString("USERGENERATED_CHAT_CHANNEL_WELCOME_MSG"),
                "/img/group20.png", ChatMessageHandler.GROUP_CHAT);
        GuiManager.getChatPanel().joinNewChatroom(this);
    }
}
