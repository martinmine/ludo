package no.hig.imt3281.ludo.client.game;

import no.hig.imt3281.ludo.client.gui.layer.chat.ChatMessage;

/**
 * Created by Joakim on 03.11.2014.
 */
public interface ChatState {
    void broadcastMessage(ChatMessage message);
}
