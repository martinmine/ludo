package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.AudioManager;
import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.Client;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Handles incoming messages of the type LoginResult
 */
public class LoginResultHandler implements MessageHandler {
    public void handle(LoginResult message, CommunicationContext context) {
        switch (message.getResultCode()) {
            case LoginResult.OK:
                GuiManager.getStartDialog().dispose();
                AudioManager.playSound("airhorn.wav", false);
                GuiManager.setClientRef(new Client());
                break;

            case LoginResult.INVALID_CREDENTIALS:
                GuiManager.getStartDialog().setFeedback(Main.resourceBundle.getString("LOGIN_FEEDBACK_INVALID"));
                JOptionPane.showMessageDialog(
                        GuiManager.getStartDialog(),
                        Main.resourceBundle.getString("LOGIN_FAILED_TO_AUTHENTICATE"));
                break;

            case LoginResult.SERVER_ERROR:
                GuiManager.getStartDialog().setFeedback(Main.resourceBundle.getString("LOGIN_FEEDBACK_ERROR"));
                JOptionPane.showMessageDialog(
                        GuiManager.getStartDialog(),
                        Main.resourceBundle.getString("LOGIN_SERVER_FAILURE"));
                break;
        }
    }
}
