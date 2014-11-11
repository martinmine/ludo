package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 11.11.2014.
 */
public class RegistrationRequestHandler implements MessageHandler {
    public void handle(RegistrationRequestHandler request, CommunicationContext context) {
        if (ServerEnvironment.getUserManager().getUser(request.get))
    }
}
