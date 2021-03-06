package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.ChallengeUserRequest;
import no.hig.imt3281.ludo.messaging.GameChallengeResponse;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Message handler for game challenge requests.
 */
public class ChallengeUserRequestHandler implements MessageHandler {
    public void handle(ChallengeUserRequest request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());

        if (user == null) {
            return;
        }

        List<User> users = new LinkedList<>();

        for (int userId : request.getUserIds()) {
            User challengingUser = ServerEnvironment.getUserManager().getUser(userId);
            if (challengingUser != null) {
                users.add(challengingUser);
            }
        }

        if (!users.isEmpty()) {
            ServerEnvironment.getGameQueueManager().challengeUsers(users, user);
        } else {
            GameChallengeResponse response = new GameChallengeResponse();
            response.setState(GameChallengeResponse.REJECTED);
            try {
                context.sendMessage(response);
            } catch (IOException e) {
                context.close(e);
            }
        }
    }
}
