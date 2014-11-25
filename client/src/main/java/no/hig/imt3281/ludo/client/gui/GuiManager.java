package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.gui.chat.ChatPanel;
import no.hig.imt3281.ludo.client.gui.game.GamePanel;

import javax.swing.*;

/**
 * Class keeping reference to the different parts of the GUI
 */
public class GuiManager {
    private static StartDialog startDialog;
    private static ChatPanel chatPanel;
    private static GamePanel gamePanel;
    private static SidePanel sidePanel;
    private static SideTopPanel sideTopPanel;
    private static Client client;

    private GuiManager() {
    }

    /** @return reference to the login activity */
    public static StartDialog getStartDialog() {
        return startDialog;
    }
    /** @return reference to chatpanel */
    public static ChatPanel getChatPanel() {
        return chatPanel;
    }
    /** @return reference to gamepanel */
    public static GamePanel getGamePanel() {
        return gamePanel;
    }
    /** @return reference to the sidepanel(right) */
    public static SidePanel getSidePanel() {
        return sidePanel;
    }
    /** @return reference to the side-top-panel(top right) */
    public static SideTopPanel getSideTopPanel() {
        return sideTopPanel;
    }

    /**
     * Initialize the GUI for the loginActivity
     * @param parent Jframe reference
     */
    public static void initializeLogin(JFrame parent) {
        startDialog = new StartDialog(parent);
        startDialog.display();
    }

    /**
     * Initializes the client GUI components
     */
    public static void initializeClient() {
        chatPanel = new ChatPanel();
        gamePanel = new GamePanel();
        sideTopPanel = new SideTopPanel();
        sidePanel = new SidePanel();

    }

    public static void exit() {
        if (client != null) {
            client.dispose();
        }
    }

    public static void setClientRef(Client clientReference) {
        client = clientReference;
    }
}
