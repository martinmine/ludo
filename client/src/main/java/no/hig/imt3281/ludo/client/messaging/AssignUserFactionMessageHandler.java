package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.messaging.AssignUserFactionMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 24.11.2014.
 */
public class AssignUserFactionMessageHandler implements MessageHandler {
    public void handle(AssignUserFactionMessage message, CommunicationContext context) {
        System.out.println("Your color is: " + message.getFaction());
    }
}
