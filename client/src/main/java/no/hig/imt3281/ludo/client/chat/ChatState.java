package no.hig.imt3281.ludo.client.chat;

/**
 * Interface for chat states.
 * Aggregated in ChatMessageHandler
 */
public interface ChatState {
    void broadcastMessage(int channelId, String message);
    int getStateType();
}
