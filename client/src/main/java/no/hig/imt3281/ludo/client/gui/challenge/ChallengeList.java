package no.hig.imt3281.ludo.client.gui.challenge;

import javax.swing.*;
import java.awt.*;

/**
 *  List of challengeable users
 */
public class ChallengeList extends JList<ChallengeableUserComponent> {
    public DefaultListModel<ChallengeableUserComponent> model;

    /**
     * Creates new ChallegeList
     */
    public ChallengeList() {
        this.model = new DefaultListModel();
        setModel(model);
    }

    /**
     * @param component to be added to the list
     */
    public void addToList(ChallengeableUserComponent component) {
        this.model.addElement(component);
    }

    /**
     * @param index of the element to be returned
     * @return element of given index
     */
    public Component getElement(int index) {
        return getModel().getElementAt(index);
    }
}
