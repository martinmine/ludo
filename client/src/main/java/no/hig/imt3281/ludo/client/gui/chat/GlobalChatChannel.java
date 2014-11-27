package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;
import no.hig.imt3281.ludo.client.gui.GuiManager;

/**
 * GlobalChatChannel is the channel where
 * users can exchange messages across all games
 */
public class GlobalChatChannel extends ChatChannel {
    public static final int GlobalChatChannelId = -1;

    /**
     * Creates a GlobalChatChennel
     */
    public GlobalChatChannel() {
        super(GlobalChatChannelId, Main.getResourceBundle().getString("GLOBAL_CHAT_CHANNEL"),
                Main.getResourceBundle().getString("GLOBAL_CHAT_CHANNEL_WELCOME_MSG"),
                "/img/world20.gif", ChatMessageHandler.GLOBAL_CHAT);
        GuiManager.getChatPanel().joinNewChatroom(this);
    }
}
