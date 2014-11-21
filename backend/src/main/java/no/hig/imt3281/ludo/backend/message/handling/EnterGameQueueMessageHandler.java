package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.EnterGameQueueMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by marti_000 on 21.11.2014.
 */
public class EnterGameQueueMessageHandler implements MessageHandler {
    public void handle(EnterGameQueueMessage request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());

        if (user != null) {
            ServerEnvironment.getGameQueueManager().enterQueue(user);
        }
    }
}
