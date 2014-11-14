package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GameChatMessage;
import no.hig.imt3281.ludo.messaging.Message;

import java.io.IOException;

/**
 * Created by Joakim on 03.11.2014.
 */
public class GameChatState implements ChatState {
    @Override
    public void broadcastMessage(int channelId, String message) {
        try {
            GameChatMessage gameChatMessage = new GameChatMessage();
            gameChatMessage.setMessage(message);
            gameChatMessage.setGameId(channelId);
            Main.getServerConnection().sendMessage(gameChatMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
