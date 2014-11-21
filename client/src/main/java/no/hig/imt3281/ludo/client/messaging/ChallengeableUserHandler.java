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
    private ChallengeUserFrame listFrame;

    void handle(ChallengeableUser message, CommunicationContext context) {
       if (this.listFrame != null)
           listFrame.addListItem(new ChallengeableUserComponent(message.getUsername(), message.getUserId()));
       else {
           this.listFrame = new ChallengeUserFrame();
           this.listFrame.addListItem(new ChallengeableUserComponent(message.getUsername(), message.getUserId()));
       }
    }
}
