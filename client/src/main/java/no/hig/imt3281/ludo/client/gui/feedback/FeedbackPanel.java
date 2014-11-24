package no.hig.imt3281.ludo.client.gui.feedback;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 05.11.2014.
 */
public class FeedbackPanel extends JPanel {
    private FeedbackTextPane textPane;

    public FeedbackPanel() {

        textPane = new FeedbackTextPane();
        add(textPane);
        textPane.setFeedbackString("Intitial string");
        setPreferredSize(new Dimension(240,160));
        setVisible(true);
    }

    public void setFontColorByFaction(int faction) {
        textPane.styleByFaction(faction);
    }

    public void setText(String text) {
        textPane.setFeedbackString(text);
    }
}
