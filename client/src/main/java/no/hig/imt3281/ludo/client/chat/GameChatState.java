package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GameChatMessage;
import java.io.IOException;

/**
 * State for the ChatMessageHandler
 *
 * Handles outgoing GameChatMessages
 */
public class GameChatState implements ChatState {
    private int type = ChatMessageHandler.GAME_CHAT;

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
            Main.getServerConnection().close(e);
        }
    }

    @Override
    public int getStateType() {
        return type;
    }


}
