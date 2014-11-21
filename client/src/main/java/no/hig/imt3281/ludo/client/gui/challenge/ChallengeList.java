package no.hig.imt3281.ludo.client.gui.challenge;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 17.11.2014.
 */
public class ChallengeList extends JList<ChallengeableUserComponent> {

    public ChallengeList() {
        final DefaultListModel<ChallengeableUserComponent> model = new DefaultListModel();
        setModel(model);

    }

    public Component getElement(int index) {
        return getModel().getElementAt(index);
    }
}
