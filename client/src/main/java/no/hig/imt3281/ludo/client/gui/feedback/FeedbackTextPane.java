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
    private static final Object SYNC_ROOT = new Object();
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
        setText("hey");

        style = styleContext.addStyle("text", null);
        style.addAttribute(StyleConstants.FontSize, new Integer(32));
        style.addAttribute(StyleConstants.FontFamily, "arial");
        style.addAttribute(StyleConstants.Bold, new Boolean(true));
        setFeedbackString("oh no");
    }

    public void styleByFaction(int faction) {

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
        setFeedbackString(feedbackString);
    }

    // Fix from http://www.jguru.com/forums/view.jsp?EID=445667
    public void setFeedbackString(String text) {
        SwingUtilities.invokeLater(() -> {
            synchronized(SYNC_ROOT) {
                this.feedbackString = text;
                try {
                    document.remove(0, document.getLength());
                    document.insertString(0,text, style);
                } catch (BadLocationException e) {
                    LOGGER.log(Level.INFO, e.getMessage());
                }
            }
        });
    }
}

