package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.messaging.Message;

/**
 * Created by Joakim on 03.11.2014.
 */
public interface ChatState {
    void broadcastMessage(int channelId, String message);
}
