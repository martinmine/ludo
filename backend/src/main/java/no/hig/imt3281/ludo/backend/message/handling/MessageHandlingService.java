package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.messaging.LoginRequest;
import no.hig.imt3281.ludo.messaging.handling.MessageHandlerFactory;

/**
 *
 */
public class MessageHandlingService extends MessageHandlerFactory {
    public MessageHandlingService() {
        super();

        registerResponse(LoginRequest.class, new LoginRequestHandler());
    }
}
