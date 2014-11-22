package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;
import no.hig.imt3281.ludo.client.gui.GuiManager;

/**
 * GameChatChanel lets users within the same game
 * communicate through text messages
 */
public class GameChatChannel extends ChatChannel {
    /**
     * Creates a new instance of a GameChatChannel and adds itself to the
     * GUI layer
     * @param id of the GameChatChannel is the same as the current game
     */
    public GameChatChannel(int id) {
        super(id, Main.resourceBundle.getString("GAME_CHAT_CHANNEL"),
                Main.resourceBundle.getString("GAME_CHAT_CHANNEL_WELCOME_MSG"),
                "/img/world20.gif", ChatMessageHandler.GAME_CHAT);
        GuiManager.getChatPanel().joinNewChatroom(this);
    }
}
