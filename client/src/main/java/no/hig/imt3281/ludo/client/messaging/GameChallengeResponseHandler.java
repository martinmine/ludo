package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GameChallengeResponse;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import javax.swing.*;

/**
 * Created by marti_000 on 21.11.2014.
 */
public class GameChallengeResponseHandler implements MessageHandler {
    public void handle(GameChallengeResponse request, CommunicationContext context) {
        JOptionPane.showConfirmDialog(null, Main.resourceBundle.getString("CHALLENGE_REJECTED"),
                Main.resourceBundle.getString("CHALLENGE_REJECTED_TITLE"),
                JOptionPane.DEFAULT_OPTION);
    }
}