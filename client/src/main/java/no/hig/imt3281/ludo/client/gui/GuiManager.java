package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.gui.chat.ChatPanel;
import no.hig.imt3281.ludo.client.gui.game.GamePanel;

import javax.swing.*;

/**
 * Created by Joakim on 10.11.2014.
 */
public class GuiManager {
    private static StartDialog startDialog;
    private static ChatPanel chatPanel;
    private static GamePanel gamePanel;
    private static SidePanel sidePanel;
    private static SideTopPanel sideTopPanel;

    public static StartDialog getStartDialog() {
        return startDialog;
    }

    public static ChatPanel getChatPanel() {
        return chatPanel;
    }

    public static GamePanel getGamePanel() {
        return gamePanel;
    }

    public static SidePanel getSidePanel() {
        return sidePanel;
    }

    public static SideTopPanel getSideTopPanel() {
        return sideTopPanel;
    }

    public static void initializeLogin(JFrame parent) {
        startDialog = new StartDialog(parent);
        startDialog.display();
    }

    public static void initializeClient() {
        chatPanel = new ChatPanel();
        gamePanel = new GamePanel();
        sideTopPanel = new SideTopPanel();
        sidePanel = new SidePanel();

    }
}
