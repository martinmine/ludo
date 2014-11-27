package no.hig.imt3281.ludo.client.gui.challenge;

import no.hig.imt3281.ludo.client.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Frame holding the instruments for handling player challenging
 */
public class ChallengeUserFrame extends JFrame {
    private static ChallengeUserFrame instance;
    private static final Logger LOGGER = Logger.getLogger(ChallengeUserFrame.class.getSimpleName());

    /**
     * Creates a new instance of the ChallengeUserFrame
     */
    private ChallengeUserFrame() {
        super(Main.getResourceBundle().getString("CHALLENGE_PLAYERS_TITLE"));
        LOGGER.info("Creating a new challenge user frame");
        this.usersAdded = new HashMap<>();
        setPreferredSize(new Dimension(300, 500));

        this.listPanel = new ChallengeUserPanel();
        add(listPanel);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ChallengeUserFrame.instance = null;
                LOGGER.info("Closing challenge user frame");
            }
        });
    }

    /**
     * @return the single instance of this or create this upon function call
     */
    public static ChallengeUserFrame getInstance() {
        if (instance == null) {
            instance = new ChallengeUserFrame();
        }
        return instance;
    }

    private ChallengeUserPanel listPanel;
    private Map<Integer, String> usersAdded;

    /**
     * Adds a new challengeable user to the list residing in
     * my listPanel child.
     *
     * @param username of new item
     * @param userId of the new item
     */
    public void addListItem(String username, int userId) {
        if (!this.usersAdded.containsKey(userId)) {
            this.usersAdded.put(userId, username);
            SwingUtilities.invokeLater(() -> listPanel.addToList(new ChallengeableUserComponent(username, userId)));
        }
    }

    /**
     * Display this
     */
    public void display() {
        LOGGER.info("Displaying challenge user frame");
        pack();
        setVisible(true);
    }
}