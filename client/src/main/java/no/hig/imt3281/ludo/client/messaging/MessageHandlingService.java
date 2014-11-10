package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.MessageHandlerFactory;

/**
 *
 */
public class MessageHandlingService extends MessageHandlerFactory {
    public MessageHandlingService() {
        super();
        registerResponse(LoginResult.class, new LoginResultHandler());
    }
}
