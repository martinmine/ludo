package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GameChatMessage;
import no.hig.imt3281.ludo.messaging.Message;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * State for the ChatMessageHandler
 *
 * Handles outgoing GameChatMessages
 */
public class GameChatState implements ChatState {

    private static final Logger LOGGER = Logger.getLogger(GameChatState.class.getSimpleName());

    /**
     * Generates a GameChatMessage and sends it to server
     *
     * @param channelId is not in use in this case
     * @param message chatmessage string
     */
    @Override
    public void broadcastMessage(int channelId, String message) {
        try {
            GameChatMessage gameChatMessage = new GameChatMessage();
            gameChatMessage.setMessage(message);
            gameChatMessage.setGameId(channelId);
            Main.getServerConnection().sendMessage(gameChatMessage);
        } catch (IOException e) {
            LOGGER.severe("Failed to broadcast game chat message");
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
