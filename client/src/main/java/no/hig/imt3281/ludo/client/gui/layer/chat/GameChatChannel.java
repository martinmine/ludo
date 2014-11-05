package no.hig.imt3281.ludo.client.gui.layer.chat;

import no.hig.imt3281.ludo.client.Main;

/**
 * Created by Joakim on 03.11.2014.
 */
public class GameChatChannel extends ChatChannel {
    public GameChatChannel() {
        super(Main.resourceBundle.getString("GAME_CHAT_CHANNEL_WELCOME_MSG"));
    }
}
