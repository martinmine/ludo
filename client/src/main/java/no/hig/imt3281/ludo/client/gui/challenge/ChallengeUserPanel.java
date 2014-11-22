package no.hig.imt3281.ludo.client.gui.challenge;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.ChallengeUserRequest;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joakim on 21.11.2014.
 */
public class ChallengeUserPanel extends JPanel {
    private JScrollPane scrollPane;
    private ChallengeList challengeableUsersList;
    private JButton challengeButton;
    private static final Logger LOGGER = Logger.getLogger(ChallengeUserPanel.class.getSimpleName());

    public ChallengeUserPanel() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        this.challengeableUsersList = new ChallengeList();
        this.challengeableUsersList.getSelectedIndices();
        this.scrollPane = new JScrollPane(challengeableUsersList);
        this.challengeButton = new JButton(Main.resourceBundle.getString("GAME_CHALLENGE_SELECTED_PLAYERS_BUTTON"));


        challengeButton.addActionListener( e -> {
           ChallengeUserRequest request = new ChallengeUserRequest();
           for(int index : challengeableUsersList.getSelectedIndices()) {
               ChallengeableUserComponent c = (ChallengeableUserComponent) challengeableUsersList.getElement(index);
               request.addUserId(c.getId());
           }
           try {
               Main.getServerConnection().sendMessage(request);
               JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
               mainFrame.dispose();
           } catch (IOException e1) {
               LOGGER.log(Level.SEVERE, e1.getMessage(), e1);
           }
        });

        add(challengeButton, BorderLayout.EAST);
        add(scrollPane, BorderLayout.WEST);
    }

    public void addToList(ChallengeableUserComponent component) {
        this.challengeableUsersList.addToList(component);
    }
}
