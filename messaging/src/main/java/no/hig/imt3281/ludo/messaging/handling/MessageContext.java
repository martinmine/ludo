package no.hig.imt3281.ludo.messaging.handling;

import no.hig.imt3281.ludo.messaging.Message;

/**
 *
 */
public interface MessageContext {
    void sendMessage(Message msg);
}
