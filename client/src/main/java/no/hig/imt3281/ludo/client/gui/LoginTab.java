package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.LoginRequest;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Login tab that lets the user log into the system
 */
public class LoginTab extends JPanel {
    private static final Logger LOGGER = Logger.getLogger(LoginTab.class.getSimpleName());
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginBtn;

    /**
     * Creates a new instance of logintab
     * @param parent reference
     * @param dialog reference to mainframe
     */
    public LoginTab(Frame parent, StartDialog dialog) {
        setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        usernameLabel = new JLabel(Main.resourceBundle.getString("LOGIN_USERNAME_LABEL"));
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        add(usernameLabel, cs);

        usernameField = new JTextField(15);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 2;
        add(usernameField, cs);

        passwordLabel = new JLabel(Main.resourceBundle.getString("LOGIN_PASSWORD_LABEL"));
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        add(passwordLabel, cs);

        passwordField = new JPasswordField(15);
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 2;
        add(passwordField, cs);

        loginBtn = new JButton(Main.resourceBundle.getString("LOGIN_BUTTON_LOGIN"));
        loginBtn.addActionListener(e -> {
            LoginRequest request = new LoginRequest();
            request.setUsername(getUsername());
            request.setPassword(getPassword());

            try {
                Main.getServerConnection().sendMessage(request);
                LOGGER.info("Sent login message");
            } catch (IOException readException) {
                LOGGER.log(Level.SEVERE, readException.getMessage(), readException);
                JOptionPane.showMessageDialog(
                        GuiManager.getStartDialog(), Main.resourceBundle.getString("COULD_NOT_CONNECT_TO_SERVER")
                );
            }
        });

        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 3;
        add(loginBtn, cs);
    }
    /** @return username */
    public String getUsername() {
        return usernameField.getText().trim();
    }
    /** @return password */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

}
