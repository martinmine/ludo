package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.messaging.GameStartedMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type GameStartedMessage
 */
public class GameStartedMessageHandler implements MessageHandler {
    public void handle(GameStartedMessage message, CommunicationContext context) {

    }
}
