package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.ListChallengeableUsersRequest;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 17.11.2014.
 */
public class ListChallengeableUserRequestHandler implements MessageHandler {
    public void handle(ListChallengeableUsersRequest request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());

        if (user != null) {
            ServerEnvironment.getUserManager().requestUserList(user);
        }
    }
}
