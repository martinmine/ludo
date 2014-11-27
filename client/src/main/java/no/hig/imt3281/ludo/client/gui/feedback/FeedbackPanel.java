package no.hig.imt3281.ludo.client.gui.feedback;

import javax.swing.*;
import java.awt.*;

/**
 * Located in the top right corner, the FeedbackPanel
 * resides. It's main mission is to serve the users fresh
 * news about the ongoing game.
 */
public class FeedbackPanel extends JPanel {
    private FeedbackTextPane textPane;

    /**
     * Creates a new FeedbackPanel
     */
    public FeedbackPanel() {
        textPane = new FeedbackTextPane();
        add(textPane);
        setPreferredSize(new Dimension(240,160));
        setVisible(true);
    }

    /**
     * Sets the color of the players text in the
     * feedback panel to the same as the player color
     * @param faction is represented as player/faction color
     */
    public void setFontColorByFaction(int faction) {
        textPane.styleByFaction(faction);
    }

    /**
     * Change the text in the feedbackPanel
     * @param text to be set in panel
     */
    public void setText(String text) {
        textPane.setFeedbackString(text);
    }
}
