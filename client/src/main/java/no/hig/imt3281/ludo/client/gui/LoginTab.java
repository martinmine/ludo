package no.hig.imt3281.ludo.client.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas on 11.11.2014.
 *
 */
public class LoginTab extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginBtn;

    public LoginTab(Frame parent, StartDialog dialog) {
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

        passwordLabel = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        add(passwordLabel, cs);

        passwordField = new JPasswordField(15);
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 2;
        add(passwordField, cs);

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
            dialog.setFeedback("feedback message about login");
            //StartDialogFeedback.getInstance().setMessage("Feedback about login");
            //parent.dispose();
        });

        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 3;
        add(loginBtn, cs);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

}
