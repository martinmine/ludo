package no.hig.imt3281.ludo.client.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Thomas on 10.11.2014.
 *
 * Forcing the user to login to play.
 * Handel register as well.
 * Based on / copied: http://www.zentut.com/java-swing/simple-login-dialog/
 */
public class StartDialog extends JDialog {

    //private StartDialogFeedback feedback = new StartDialogFeedback();
    private JLabel feedback;

    public StartDialog(JFrame parent) {
        super(parent, "Login", true);

        ImageIcon logo = new ImageIcon(getClass().getResource("/img/ludo_logo.jpg"));
        JLabel label = new JLabel("", logo, JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label);

        JTabbedPane tabbedPane = new JTabbedPane();
        LoginTab loginTab = new LoginTab(parent, this);
        RegisterTab registerTab = new RegisterTab(parent, this);

        tabbedPane.addTab("Login", null, loginTab, "Tab 1");
        tabbedPane.addTab("Register", null, registerTab, "Tab 2");

        JPanel side = new JPanel();
        feedback = new JLabel();
        side.setLayout(new BorderLayout());
        side.add(feedback, BorderLayout.NORTH);
        side.add(tabbedPane, BorderLayout.SOUTH);

        getContentPane().add(panel, BorderLayout.WEST);
        getContentPane().add(side, BorderLayout.EAST);

        setResizable(false);
        setSize(700,300);
        //setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public void setFeedback(String message) {
        feedback.setText(message);
    }

}
