package no.hig.imt3281.ludo.client.gui.challenge;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.ChallengeUserRequest;
import no.hig.imt3281.ludo.messaging.ListChallengeableUsersRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Created by Joakim on 21.11.2014.
 */
public class ChallengeUserPanel extends JPanel {
    private JScrollPane scrollPane;
    private ChallengeList challengeableUsersList;
    private JButton challengeButton;

    public ChallengeUserPanel() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        this.challengeableUsersList = new ChallengeList();
        this.challengeableUsersList.getSelectedIndices();
        this.scrollPane = new JScrollPane(challengeableUsersList);
        this.challengeButton = new JButton("1v1usFeggit");


        challengeButton.addActionListener( e -> {
           ChallengeUserRequest request = new ChallengeUserRequest();
           for(int index : challengeableUsersList.getSelectedIndices()) {
               ChallengeableUserComponent c = (ChallengeableUserComponent) challengeableUsersList.getElement(index);
               request.addUserId(c.getId());

           }
            try {
                Main.getServerConnection().sendMessage(request);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        add(challengeButton, BorderLayout.EAST);
        add(scrollPane, BorderLayout.WEST);

    }

    public void addToList(Component component) {
        this.challengeableUsersList.add(component);
    }
}
