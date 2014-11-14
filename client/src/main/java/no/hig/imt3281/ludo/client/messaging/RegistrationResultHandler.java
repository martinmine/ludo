package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.RegistrationResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 11.11.2014.
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
            case RegistrationResult.SERVER_ERROR:
                GuiManager.getStartDialog().setFeedback("SERVER_ERROR");
                break;
            case RegistrationResult.OK:
                GuiManager.getStartDialog().dispose();
                break;
        }
    }
}