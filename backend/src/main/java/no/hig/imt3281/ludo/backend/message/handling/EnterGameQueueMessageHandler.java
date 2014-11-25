package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.EnterGameQueueMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Message handler for when a user enters the random game queue.
 */
public class EnterGameQueueMessageHandler implements MessageHandler {
    public void handle(EnterGameQueueMessage request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());

        if (user != null) {
            ServerEnvironment.getGameQueueManager().enterQueue(user);
        }
    }
}
