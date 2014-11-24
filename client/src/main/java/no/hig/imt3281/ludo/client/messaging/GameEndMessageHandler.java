package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GameEndMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import javax.swing.*;

/**
 * Handles incoming messages of the type GameEndMessage
 */
public class GameEndMessageHandler implements MessageHandler {
    public void handle(GameEndMessage message, CommunicationContext context) {
        String outputMessage;

        switch (message.getGameResult()) {
            case GameEndMessage.KICKED:
                outputMessage = "GAME_KICKED";
                break;
            case GameEndMessage.WON:
                outputMessage = "GAME_WON";
                break;
            default:
            case GameEndMessage.LOST:
                outputMessage = "GAME_LOST";
                break;
        }

        JOptionPane.showMessageDialog(null, Main.resourceBundle.getString(outputMessage));
    }
}
