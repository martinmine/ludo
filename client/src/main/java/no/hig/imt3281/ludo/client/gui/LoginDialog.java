package no.hig.imt3281.ludo.client.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas on 10.11.2014.
 *
 * Forcing the user to login to play.
 * Handel register as well.
 * Based on / copied: http://www.zentut.com/java-swing/simple-login-dialog/
 */
public class LoginDialog extends JDialog {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginBtn;
    private JButton cancelBtn;
    //private boolean succeeded;

    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        usernameLabel = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(usernameLabel, cs);

        usernameField = new JTextField(15);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(usernameField, cs);

        passwordLabel = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(passwordLabel, cs);

        passwordField = new JPasswordField(15);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(passwordField, cs);

        loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> {
            System.out.println("server call to authenticate user...");
            /* something like:
            if ( serverSomething(getUsername(), getPassword() ) ) {
                parent.dispose();
            } else {
                usernameField.setText("");
                passwordField.setText("");
            }
            */
            parent.dispose();
        });

        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> {
            parent.dispose();
            System.exit(0);
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(loginBtn);
        btnPanel.add(cancelBtn);

        getContentPane().add(panel);
        getContentPane().add(btnPanel, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

}
