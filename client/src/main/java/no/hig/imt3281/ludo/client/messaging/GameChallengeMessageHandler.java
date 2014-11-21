package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GameChallengeMessage;
import no.hig.imt3281.ludo.messaging.GameChallengeResponse;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import javax.swing.JOptionPane;
import java.io.IOException;

/**
 * Created by Joakim on 21.11.2014.
 */
public class GameChallengeMessageHandler implements MessageHandler {

    public static final int CHALLENGE_ACCEPTED = 0;

    public void handle(GameChallengeMessage message, CommunicationContext context) {
        int result = new JOptionPane().showConfirmDialog(null,
                            message.getChallengerUsername() + " " + Main.resourceBundle.getString("CHALLENGE_INVITATION_MESSAGE"),
                            Main.resourceBundle.getString("CHALLENGE_INVITATION_TITLE"),
                            JOptionPane.YES_NO_OPTION);

        GameChallengeResponse response = new GameChallengeResponse();

        if (result == CHALLENGE_ACCEPTED) {
            response.setState(GameChallengeResponse.ACCEPTED);
        } else {
            response.setState(GameChallengeResponse.REJECTED);
        }

        try {
            Main.getServerConnection().sendMessage(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
