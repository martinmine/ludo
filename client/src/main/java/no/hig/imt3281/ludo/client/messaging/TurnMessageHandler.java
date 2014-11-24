package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.TurnMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type TurnMessage
 */
public class TurnMessageHandler implements MessageHandler {
    public void handle(TurnMessage message, CommunicationContext context) {
        GuiManager.getSideTopPanel().getFeedbackPanel().setText(Main.resourceBundle.getString("PLAYER_TURN"));
    }
}
