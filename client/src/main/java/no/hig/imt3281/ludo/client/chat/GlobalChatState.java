package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GlobalChatMessage;
import no.hig.imt3281.ludo.messaging.Message;

import java.io.IOException;

/**
 * Created by Joakim on 03.11.2014.
 */
public class GlobalChatState implements ChatState {
    @Override
    public void broadcastMessage(int channelId, String message) {
        try {
            GlobalChatMessage globalChatMessage = new GlobalChatMessage();
            globalChatMessage.setMessage(message);
            Main.getServerConnection().sendMessage(globalChatMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
