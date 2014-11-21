package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.gui.challenge.ChallengeUserFrame;
import no.hig.imt3281.ludo.client.gui.challenge.ChallengeableUserComponent;
import no.hig.imt3281.ludo.messaging.ChallengeableUser;
import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Joakim on 17.11.2014.
 */
public class ChallengeableUserHandler implements MessageHandler {

    public void handle(ChallengeableUser message, CommunicationContext context) {
        ChallengeUserFrame listFrame = ChallengeUserFrame.getInstance();
        listFrame.addListItem(message.getUsername(), message.getUserId());
    }
}
