package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 08.11.2014.
 */
public class LoginResultHandler implements MessageHandler {
    public void handle(LoginResult message, CommunicationContext context) {
        switch (message.getResultCode()) {
            case LoginResult.OK: {
                GuiManager.getStartDialog().dispose();
                // TODO: Make login window hide and make
                break;
            }
            case LoginResult.INVALID_CREDENTIALS: {
                GuiManager.getStartDialog().setFeedback("INVALID_UPW");
                // TODO: Show message dialog
                break;
            }
            case LoginResult.SERVER_ERROR: {
                GuiManager.getStartDialog().setFeedback("ERROR");
                // TODO: Show message dialog
                break;
            }
        }
    }
}
