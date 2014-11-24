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
    private Style style;
    String feedbackString;

    public FeedbackTextPane() {
        feedbackString = "yo";
        styleContext = new StyleContext();
        document = new DefaultStyledDocument(styleContext);
        setDocument(document);
        border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Feedback panel");
        setBorder(border);
        setEditable(false);
        setOpaque(false);
        setBackground(Color.cyan);
        setPreferredSize(new Dimension(240, 160));
    }

    public void styleByFaction(int faction) {
        style = styleContext.addStyle("text", null);

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

        style.addAttribute(StyleConstants.FontSize, new Integer(32));
        style.addAttribute(StyleConstants.Background, new Color(Color.PINK.getBlue()));
        style.addAttribute(StyleConstants.FontFamily, "arial");
        style.addAttribute(StyleConstants.Bold, new Boolean(true));
        try {
            document.insertString(0, feedbackString, style);
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
            document.insertString(0,text, style);
            LOGGER.info("Finalizing");
        } catch (BadLocationException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
        LOGGER.info("Returning");
    }
}

