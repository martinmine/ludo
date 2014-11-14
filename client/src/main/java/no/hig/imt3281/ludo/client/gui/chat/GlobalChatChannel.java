package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;
import no.hig.imt3281.ludo.client.gui.GuiManager;

/**
 * Created by Joakim on 03.11.2014.
 */
public class GlobalChatChannel extends ChatChannel {

    public static int GlobalChatChannelId = -1;
    public GlobalChatChannel() {
        super(GlobalChatChannelId, Main.resourceBundle.getString("GLOBAL_CHAT_CHANNEL"),
                Main.resourceBundle.getString("GLOBAL_CHAT_CHANNEL_WELCOME_MSG"),
                "/img/world20.gif", ChatMessageHandler.GLOBAL_CHAT);
        GuiManager.getChatPanel().joinNewChatroom(this);
    }
}
