package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.messaging.GameChallengeMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import javax.swing.JOptionPane;

/**
 * Created by Joakim on 21.11.2014.
 */
public class GameChallengeMessageHandler implements MessageHandler {

    public void handle(GameChallengeMessage message, CommunicationContext context) {
        int result = new JOptionPane().showConfirmDialog(null, message.getChallengerUsername() + "has challenged u in ludo. He said u wre faggit");
        message.getChallengerUsername();
        System.out.println(result);
    }
}
