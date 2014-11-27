package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.gui.Client;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.RegistrationResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type RegistrationResult
 */
public class RegistrationResultHandler implements MessageHandler {
    public void handle(RegistrationResult result, CommunicationContext context) {
        switch (result.getResult()) {
            case RegistrationResult.INVALID_MAIL:
                GuiManager.getStartDialog().setFeedback("INVALID_MAIL");
                break;
            case RegistrationResult.USERNAME_TAKEN:
                GuiManager.getStartDialog().setFeedback("USERNAME_TAKEN");
                break;
            case RegistrationResult.WEAK_PASSWORD:
                GuiManager.getStartDialog().setFeedback("WEAK_PASSWORD");
                break;
            case RegistrationResult.INVALID_USERNAME:
                GuiManager.getStartDialog().setFeedback("INVALID_USERNAME");
                break;
            case RegistrationResult.OK:
                GuiManager.getStartDialog().dispose();
                new Client();
                break;
            case RegistrationResult.SERVER_ERROR:
            default:
                GuiManager.getStartDialog().setFeedback("SERVER_ERROR");
                break;
        }
    }
}
