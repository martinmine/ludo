package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.MoveTokenResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type MoveTokenResult
 */
public class MoveTokenResultHandler implements MessageHandler {
    public void handle(MoveTokenResult message, CommunicationContext context) {
        if (message.isValidMove()) {
            GuiManager.getSideTopPanel().getFeedbackPanel().setText(Main.resourceBundle.getString("GAME_WAITING"));
        } else {
            GuiManager.getSideTopPanel().getFeedbackPanel().setText(Main.resourceBundle.getString("INVALID_MOVE"));
        }
    }
}
