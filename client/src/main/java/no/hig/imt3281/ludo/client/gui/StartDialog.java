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
public class StartDialog extends JDialog {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginBtn;
    private JButton cancelBtn;
    //private boolean succeeded;

    public StartDialog(JFrame parent) {
        super(parent, "Login", true);

        ImageIcon logo = new ImageIcon(getClass().getResource("/img/ludo_logo.jpg"));
        JLabel label = new JLabel("", logo, JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label);

        JTabbedPane tabbedPane = new JTabbedPane();
        LoginTab loginTab = new LoginTab(parent);
        RegisterTab registerTab = new RegisterTab(parent);

        tabbedPane.addTab("Login", null, loginTab, "Tab 1");
        tabbedPane.addTab("Register", null, registerTab, "Tab 2");

        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().add(tabbedPane, BorderLayout.EAST);

        setResizable(false);
        setSize(650,300);
        //setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

}
