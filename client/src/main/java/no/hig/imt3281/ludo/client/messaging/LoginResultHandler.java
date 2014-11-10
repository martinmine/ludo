package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 08.11.2014.
 */
public class LoginResultHandler implements MessageHandler {
    public void handle(LoginResult message, CommunicationContext context) {
        System.out.println("Received result: " + message.getResultCode());
    }
}
