package no.hig.imt3281.ludo.client.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas on 11.11.2014.
 *
 */
public class RegisterTab extends JPanel {

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField passwordField2;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel passwordLabel2;
    private JButton registerBtn;

    public RegisterTab(Frame parent, StartDialog dialog) {
        setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        usernameLabel = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        add(usernameLabel, cs);

        usernameField = new JTextField(15);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 2;
        add(usernameField, cs);

        emailLabel = new JLabel("Email: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 2;
        add(emailLabel, cs);

        emailField = new JTextField(15);
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 3;
        add(emailField, cs);

        passwordLabel = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 2;
        add(passwordLabel, cs);

        passwordField = new JPasswordField(15);
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 2;
        add(passwordField, cs);

        passwordLabel2 = new JLabel("Confirm password: ");
        cs.gridx = 0;
        cs.gridy = 6;
        cs.gridwidth = 2;
        add(passwordLabel2, cs);

        passwordField2 = new JPasswordField(15);
        cs.gridx = 0;
        cs.gridy = 7;
        cs.gridwidth = 2;
        add(passwordField2, cs);

        registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> {
            System.out.println("Something");
            dialog.setFeedback("register feedback");
            //parent.dispose();
        });

        cs.gridx = 0;
        cs.gridy = 8;
        cs.gridwidth = 3;
        add(registerBtn, cs);

    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPassword() {
        if (passwordField.getPassword() == passwordField2.getPassword()) {
            return new String(passwordField.getPassword());
        }
        return null;
    }

}
