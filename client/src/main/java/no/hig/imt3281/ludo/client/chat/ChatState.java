package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.chat.ChatMessage;

/**
 * Created by Joakim on 03.11.2014.
 */
public interface ChatState {
    void broadcastMessage(ChatMessage message);
}
