package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Thomas on 10.11.2014.
 *
 * Forcing the user to login to play.
 * Handel register as well.
 * Based on: http://www.zentut.com/java-swing/simple-login-dialog/
 */
public class StartDialog extends JDialog {

    //private StartDialogFeedback feedback = new StartDialogFeedback();
    private JLabel feedback;

    public StartDialog(JFrame parent) {
        super(parent, Main.resourceBundle.getString("START_DIALOG_TITLE"), true);

        ImageIcon logo = new ImageIcon(getClass().getResource("/img/ludo_logo.jpg"));
        JLabel label = new JLabel("", logo, JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label);

        JTabbedPane tabbedPane = new JTabbedPane();
        LoginTab loginTab = new LoginTab(parent, this);
        RegisterTab registerTab = new RegisterTab(parent, this);

        tabbedPane.addTab(Main.resourceBundle.getString("LOGIN_TAB"), null, loginTab);
        tabbedPane.addTab(Main.resourceBundle.getString("REGISTRATION_TAB"), null, registerTab);

        JPanel side = new JPanel();
        feedback = new JLabel();
        side.setLayout(new BorderLayout());
        side.add(feedback, BorderLayout.NORTH);
        side.add(tabbedPane, BorderLayout.SOUTH);

        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().add(side, BorderLayout.EAST);

        setResizable(false);
        setSize(700,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
    }

    public void setFeedback(String message) {
        feedback.setText(message);
    }

    public void display() {
        setVisible(true);
    }
}
