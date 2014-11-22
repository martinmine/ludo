package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.gui.dice.DicePanel;
import no.hig.imt3281.ludo.client.gui.feedback.FeedbackPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Contains the panels to the top right side in the GUI layout.
 * Consists of a dice panel and a feedback panel
 */
public class SideTopPanel extends JPanel {

    private DicePanel dicePanel;
    private FeedbackPanel feedbackPanel;

    /**
     * Create a new instance of the SideTopPanel
     */
    SideTopPanel() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        this.dicePanel = new DicePanel();
        this.feedbackPanel = new FeedbackPanel();

        add(dicePanel, BorderLayout.WEST);
        add(feedbackPanel, BorderLayout.CENTER);
    }
    /** @return reference to the dice panel */
    public DicePanel getDicePanel() {
        return this.dicePanel;
    }
    /** @return reference to the feedback panel */
    public FeedbackPanel getFeedbackPanel() {
        return this.feedbackPanel;
    }
}
