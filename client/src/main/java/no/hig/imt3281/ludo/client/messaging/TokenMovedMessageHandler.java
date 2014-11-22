package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.client.gui.game.GamePanel;
import no.hig.imt3281.ludo.messaging.TokenMovedMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type TokenMovedMessage
 */
public class TokenMovedMessageHandler implements MessageHandler {
    public void handle(TokenMovedMessage message, CommunicationContext context) {
        GuiManager.getGamePanel().moveToken(message.getFactionMoving(), message.getTokenId(), message.getNewPosition());
    }
}
