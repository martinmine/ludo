package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.messaging.GameEndMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type GameEndMessage
 */
public class GameEndMessageHandler implements MessageHandler {
    public void handle(GameEndMessage message, CommunicationContext context) {

    }
}
