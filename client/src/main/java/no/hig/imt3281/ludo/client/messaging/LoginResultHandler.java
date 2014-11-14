package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Martin on 08.11.2014.
 */
public class LoginResultHandler implements MessageHandler {
    public void handle(LoginResult message, CommunicationContext context) {
        switch (message.getResultCode()) {
            case LoginResult.OK: {
                GuiManager.getStartDialog().dispose();
                break;
            }
            case LoginResult.INVALID_CREDENTIALS: {
                GuiManager.getStartDialog().setFeedback("INVALID_UPW");
                JOptionPane.showMessageDialog(
                        GuiManager.getStartDialog(),
                        Main.resourceBundle.getString("LOGIN_FAILED_TO_AUTHENTICATE")
                );
                break;
            }
            case LoginResult.SERVER_ERROR: {
                GuiManager.getStartDialog().setFeedback("ERROR");
                JOptionPane.showMessageDialog(
                        GuiManager.getStartDialog(),
                        Main.resourceBundle.getString("LOGIN_SERVER_FAILURE"
                ));
                break;
            }
        }
    }
}
