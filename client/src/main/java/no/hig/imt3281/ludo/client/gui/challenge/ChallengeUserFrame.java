package no.hig.imt3281.ludo.client.gui.challenge;



import no.hig.imt3281.ludo.client.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joakim on 17.11.2014.
 */
public class ChallengeUserFrame extends JFrame {
    private static ChallengeUserFrame instance;

    public static ChallengeUserFrame getInstance() {
        if (instance == null) {
            instance = new ChallengeUserFrame();
        }

        return instance;
    }

    private ChallengeList challengeableUsersList;
    private ChallengeUserPanel listPanel;
    private Map<Integer, String> usersAdded;

    private ChallengeUserFrame() {
        super(Main.resourceBundle.getString("CHALLENGE_PLAYERS_TITLE"));
        this.usersAdded = new HashMap<>();
        setPreferredSize(new Dimension(300, 500));

        this.listPanel = new ChallengeUserPanel();
        add(listPanel);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ChallengeUserFrame.instance = null;
            }
        });
    }

    public void addListItem(String username, int userId) {
        if (!this.usersAdded.containsKey(userId)) {
            this.usersAdded.put(userId, username);
            listPanel.addToList(new ChallengeableUserComponent(username, userId));
        }
    }
}