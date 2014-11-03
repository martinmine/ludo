package no.hig.imt3281.ludo.client.chat;

/**
 * Created by Joakim on 03.11.2014.
 */
public interface ChatState {
    void broadcastMessage(ChatMessage message);
}
