package no.hig.imt3281.ludo.client.gui.challenge;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 17.11.2014.
 */
public class ChallengeableUserComponent extends JLabel{

    private int id;
    private String username;

    public ChallengeableUserComponent(String username, int id) {
        this.id = id;
        this.username = username;
        add(new Checkbox());
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return this.username + "  " + id;
    }
}
