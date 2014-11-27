package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.PlayerKickedBackToBaseMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Receives messages when players are kicked back to their home base.
 */
public class PlayerKickedBackToBaseMessageHandler implements MessageHandler {
    public void handle(PlayerKickedBackToBaseMessage message, CommunicationContext context) {
        GuiManager.getGamePanel().kickPlayerToken(message.getFaction(), message.getTokenId());
    }
}
