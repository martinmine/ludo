package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.Message;

import java.io.IOException;

/**
 * Created by Joakim on 03.11.2014.
 */
public class GlobalChatState implements ChatState {
    @Override
    public void broadcastMessage(Message message) {
        try {
            Main.getServerConnection().sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
