package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.gui.challenge.ChallengeUserFrame;
import no.hig.imt3281.ludo.messaging.ChallengeableUser;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type ChallengeableUser
 */
public class ChallengeableUserHandler implements MessageHandler {
    public void handle(ChallengeableUser message, CommunicationContext context) {
        ChallengeUserFrame listFrame = ChallengeUserFrame.getInstance();
        listFrame.addListItem(message.getUsername(), message.getUserId());
    }
}
