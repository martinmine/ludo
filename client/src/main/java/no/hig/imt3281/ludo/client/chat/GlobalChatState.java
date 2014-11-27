package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GlobalChatMessage;
import java.io.IOException;

/**
 * State for ChatMessageHandler
 * Handles outgoing GlobalChatMessages
 */
public class GlobalChatState implements ChatState {
    private int type = ChatMessageHandler.GLOBAL_CHAT;

    /**
     * Creates and sends a global chat message to the server
     * @param channelId will be a const value for globalchat
     * @param message chatmessage string
     */
    @Override
    public void broadcastMessage(int channelId, String message) {
        try {
            GlobalChatMessage globalChatMessage = new GlobalChatMessage();
            globalChatMessage.setMessage(message);
            Main.getServerConnection().sendMessage(globalChatMessage);
        } catch (IOException e) {
            Main.getServerConnection().close(e);
        }
    }

    @Override
    public int getStateType() {
        return type;
    }
}
