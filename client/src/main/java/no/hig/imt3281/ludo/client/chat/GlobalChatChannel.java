package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;

import javax.swing.*;

/**
 * Created by Joakim on 03.11.2014.
 */
public class GlobalChatChannel extends ChatChannel {
    public GlobalChatChannel() {
        super(Main.resourceBundle.getString("GLOBAL_CHAT_CHANNEL_WELCOME_MSG"));
    }
}
