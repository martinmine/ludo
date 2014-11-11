package no.hig.imt3281.ludo.client.messaging;

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
                // TODO: Show dialog box
                break;
            case RegistrationResult.USERNAME_TAKEN:
                // TODO: Show dialog box
                break;
            case RegistrationResult.WEAK_PASSWORD:
                // TODO: Show dialog box
                break;
            case RegistrationResult.INVALID_USERNAME:
                // TODO: Show dialog box
                break;
            case RegistrationResult.SERVER_ERROR:
                // TODO: Show dialog box
                break;
            case RegistrationResult.OK:
                // TODO: sign in user
                break;
        }
    }
}
