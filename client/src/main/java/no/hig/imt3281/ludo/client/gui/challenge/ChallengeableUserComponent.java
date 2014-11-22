package no.hig.imt3281.ludo.client.gui.challenge;

import javax.swing.*;
import java.awt.*;

/**
 * ListItem for the challengeable users list
 */
public class ChallengeableUserComponent extends JLabel{

    private int id;
    private String username;

    /**
     * @param username of challengeable user
     * @param id of challengeable user
     */
    public ChallengeableUserComponent(String username, int id) {
        this.id = id;
        this.username = username;
        add(new Checkbox());
    }

    /**
     * @return Id of the challengeable user
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return username, this is what
     * will be displayed in the list as a label
     */
    public String toString() {
        return this.username;
    }
}
