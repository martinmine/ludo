package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.game.queue.GameChallenge;
import no.hig.imt3281.ludo.messaging.GameChallengeResponse;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Message handler for the confirmation message when a user accepts/denies a game challenge.
 */
public class GameChallengeResponseHandler implements MessageHandler {
    public void handle(GameChallengeResponse request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());

        if (user != null) {
            GameChallenge challenge = ServerEnvironment.getGameQueueManager().getChallenge(request.getChallengeId());

            if (request.getState() == GameChallengeResponse.ACCEPTED) {
                challenge.accept(user);
            } else {
                challenge.deny(user);
            }
        }
    }
}
