package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.gui.SidePanel;
import no.hig.imt3281.ludo.client.gui.SideTopPanel;
import no.hig.imt3281.ludo.client.gui.chat.ChatPanel;
import no.hig.imt3281.ludo.client.gui.game.GamePanel;

/**
 * Created by Joakim on 10.11.2014.
 */
public class GuiManager {

    private static ChatPanel chatPanel;
    private static GamePanel gamePanel;
    private static SidePanel sidePanel;
    private static SideTopPanel sideTopPanel;

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

    public static void initialize() {
        chatPanel = new ChatPanel();
        gamePanel = new GamePanel();
        sideTopPanel = new SideTopPanel();
        sidePanel = new SidePanel();
    }
}
