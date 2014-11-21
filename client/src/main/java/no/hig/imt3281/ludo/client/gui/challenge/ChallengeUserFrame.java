package no.hig.imt3281.ludo.client.gui.challenge;



import no.hig.imt3281.ludo.client.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 17.11.2014.
 */
public class ChallengeUserFrame extends JFrame {
    private ChallengeList challengeableUsersList;
    private ChallengeUserPanel listPanel;

    public ChallengeUserFrame() {
        super(Main.resourceBundle.getString("CHALLENGE_PLAYERS_TITLE"));
        setPreferredSize(new Dimension(300, 500));

        this.listPanel = new ChallengeUserPanel();
        add(listPanel);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void addListItem(ChallengeableUserComponent component) {
        listPanel.addToList(component);
    }
}