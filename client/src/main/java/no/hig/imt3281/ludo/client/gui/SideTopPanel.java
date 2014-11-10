package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.gui.dice.DicePanel;
import no.hig.imt3281.ludo.client.gui.feedback.FeedbackPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 05.11.2014.
 */
public class SideTopPanel extends JPanel {

    private DicePanel dicePanel;
    private FeedbackPanel feedbackPanel;

    SideTopPanel() {

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        this.dicePanel = new DicePanel();
        this.feedbackPanel = new FeedbackPanel();

        add(dicePanel, BorderLayout.WEST);
        add(feedbackPanel, BorderLayout.CENTER);
    }
}
