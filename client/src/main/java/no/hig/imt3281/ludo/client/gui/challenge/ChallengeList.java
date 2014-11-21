package no.hig.imt3281.ludo.client.gui.challenge;

import javax.swing.*;

/**
 * Created by Joakim on 17.11.2014.
 */
public class ChallengeList extends JList<ChallengeableUserComponent> {

    public ChallengeList() {
        final DefaultListModel<ChallengeableUserComponent> model = new DefaultListModel();

        setModel(model);

        model.addElement(new ChallengeableUserComponent("testuser", 3));
        model.addElement(new ChallengeableUserComponent("testuser", 4));
        model.addElement(new ChallengeableUserComponent("testuser", 5));
        model.addElement(new ChallengeableUserComponent("testuser", 6));
        model.addElement(new ChallengeableUserComponent("testuser", 7));
        model.addElement(new ChallengeableUserComponent("testuser", 8));
        model.addElement(new ChallengeableUserComponent("testuser", 9));
        model.addElement(new ChallengeableUserComponent("testuser", 10));
        model.addElement(new ChallengeableUserComponent("testuser", 11));
        model.addElement(new ChallengeableUserComponent("testuser", 12));
        model.addElement(new ChallengeableUserComponent("testuser", 13));
        model.addElement(new ChallengeableUserComponent("testuser", 14));
        model.addElement(new ChallengeableUserComponent("testuser", 15));
        model.addElement(new ChallengeableUserComponent("testuser", 16));
        model.addElement(new ChallengeableUserComponent("testuser", 17));
        model.addElement(new ChallengeableUserComponent("testuser", 18));
        model.addElement(new ChallengeableUserComponent("testuser", 3));
        model.addElement(new ChallengeableUserComponent("testuser", 4));
        model.addElement(new ChallengeableUserComponent("testuser", 5));
        model.addElement(new ChallengeableUserComponent("testuser", 6));
        model.addElement(new ChallengeableUserComponent("testuser", 7));
        model.addElement(new ChallengeableUserComponent("testuser", 8));
        model.addElement(new ChallengeableUserComponent("testuser", 9));
        model.addElement(new ChallengeableUserComponent("testuser", 10));
        model.addElement(new ChallengeableUserComponent("testuser", 11));
        model.addElement(new ChallengeableUserComponent("testuser", 12));
        model.addElement(new ChallengeableUserComponent("testuser", 13));
        model.addElement(new ChallengeableUserComponent("testuser", 14));
        model.addElement(new ChallengeableUserComponent("testuser", 15));
        model.addElement(new ChallengeableUserComponent("testuser", 16));
        model.addElement(new ChallengeableUserComponent("testuser", 17));
        model.addElement(new ChallengeableUserComponent("testuser", 18));
    }
}
