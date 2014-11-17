package no.hig.imt3281.ludo.client.gui.challenge;

import javax.swing.*;

/**
 * Created by Joakim on 17.11.2014.
 */
public class ChallengeUserFrame extends JFrame {
    private JDialog dialog;

    public ChallengeUserFrame() {

        dialog = new JDialog(this, "hei", true);


        add(dialog);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
