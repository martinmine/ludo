package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;

/**
 * Created by Joakim on 05.11.2014.
 */
public class UserGeneratedChatChannel extends ChatChannel {

    public UserGeneratedChatChannel() {
        super(Main.resourceBundle.getString("USERGENERATED_CHAT_CHANNEL_WELCOME_MSG"));
    }
}
