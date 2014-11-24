package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.AudioManager;
import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GameChallengeMessage;
import no.hig.imt3281.ludo.messaging.GameChallengeResponse;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles incoming messages of the type GameChallengeMessage
 */
public class GameChallengeMessageHandler implements MessageHandler {
    public static final int CHALLENGE_ACCEPTED = 0;
    private static final Logger LOGGER = Logger.getLogger(GameChallengeMessageHandler.class.getSimpleName());

    public void handle(GameChallengeMessage message, CommunicationContext context) {
        AudioManager.playSound("challenge.wav");
        int result = new JOptionPane().showConfirmDialog(null,
                            message.getChallengerUsername() + " " + Main.resourceBundle.getString("CHALLENGE_INVITATION_MESSAGE"),
                            Main.resourceBundle.getString("CHALLENGE_INVITATION_TITLE"),
                            JOptionPane.YES_NO_OPTION);

        GameChallengeResponse response = new GameChallengeResponse();

        response.setChallengeId(message.getChallengeId());
        if (result == CHALLENGE_ACCEPTED) {
            response.setState(GameChallengeResponse.ACCEPTED);
        } else {
            response.setState(GameChallengeResponse.REJECTED);
        }

        try {
            Main.getServerConnection().sendMessage(response);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
