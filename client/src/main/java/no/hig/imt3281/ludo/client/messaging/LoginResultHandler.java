package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.AudioManager;
import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.User;
import no.hig.imt3281.ludo.client.gui.Client;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import javax.swing.JOptionPane;

/**
 * Handles incoming messages of the type LoginResult
 */
public class LoginResultHandler implements MessageHandler {
    public void handle(LoginResult message, CommunicationContext context) {
        switch (message.getResultCode()) {
            case LoginResult.OK:
                GuiManager.getStartDialog().dispose();
                AudioManager.playSound("login.wav", false);
                GuiManager.setClientRef(new Client());
                User.getInstance().initialize(message.getUserId());
                break;
            case LoginResult.INVALID_CREDENTIALS:
                GuiManager.getStartDialog().setFeedback(Main.getResourceBundle().getString("LOGIN_FEEDBACK_INVALID"));
                JOptionPane.showMessageDialog(
                        GuiManager.getStartDialog(),
                        Main.getResourceBundle().getString("LOGIN_FAILED_TO_AUTHENTICATE"));
                break;

            case LoginResult.SERVER_ERROR:
            default:
                GuiManager.getStartDialog().setFeedback(Main.getResourceBundle().getString("LOGIN_FEEDBACK_ERROR"));
                JOptionPane.showMessageDialog(
                        GuiManager.getStartDialog(),
                        Main.getResourceBundle().getString("LOGIN_SERVER_FAILURE"));
                break;
        }
    }
}
