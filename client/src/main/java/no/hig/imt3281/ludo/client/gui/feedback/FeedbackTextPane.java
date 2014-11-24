package no.hig.imt3281.ludo.client.gui.feedback;

import no.hig.imt3281.ludo.client.gui.game.Faction;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joakim on 24.11.2014.
 */
public class FeedbackTextPane extends JTextPane {
    private static final Logger LOGGER = Logger.getLogger(FeedbackTextPane.class.getSimpleName());
    private Border border;
    private StyleContext styleContext;
    private DefaultStyledDocument document;
    String feedbackString;

    public FeedbackTextPane() {
        styleContext = new StyleContext();
        document = new DefaultStyledDocument(styleContext);
        setDocument(document);
        border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Feedback panel");
        setBorder(border);
        setEditable(false);
        setOpaque(false);
        setBackground(Color.cyan);
        setPreferredSize(new Dimension(240, 160));
        feedbackString = String.valueOf(Faction.BLUE);
        System.out.println(Faction.BLUE);
    }

    public void styleByFaction(int faction) {
        final Style style = styleContext.addStyle("Heading2", null);

        switch(Faction.getFaction(faction)) {
            case GREEN:
                style.addAttribute(StyleConstants.Foreground, Color.green);
            break;
            case RED:
                style.addAttribute(StyleConstants.Foreground, Color.red);
            break;
            case BLUE:
                style.addAttribute(StyleConstants.Foreground, Color.blue);
            break;
            case YELLOW:
                style.addAttribute(StyleConstants.Foreground, Color.yellow);
            break;
            default:
                break;
        }

        style.addAttribute(StyleConstants.FontSize, new Integer(42));
        style.addAttribute(StyleConstants.FontFamily, "arial");
        style.addAttribute(StyleConstants.Bold, new Boolean(true));
        try {
            document.insertString(0, feedbackString, null);
            document.setParagraphAttributes(0, 1, style, false);
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void setFeedbackString(String text) {
        LOGGER.info("REmoving old text");
        feedbackString = text;
        try {
            LOGGER.info("SEtting");
            document.remove(0,document.getLength());
            LOGGER.info("Finalizing");
            document.insertString(0,text, null);
        } catch (BadLocationException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
        LOGGER.info("Returning");
    }
}

