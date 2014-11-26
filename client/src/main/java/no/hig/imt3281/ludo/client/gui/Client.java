package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.chat.ChatRooms;
import no.hig.imt3281.ludo.client.gui.game.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Class containing the root element of the client GUI
 */
public class Client extends JFrame {
    private MenuBar menuBar;

    /**
     * Creates a new instance of Client
     */
    public Client() {
        super(Main.resourceBundle.getString("NAME_OF_THE_GAME"));

        GuiManager.initializeClient();
        ChatRooms.getInstance();

        menuBar = new no.hig.imt3281.ludo.client.gui.MenuBar();

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        GamePanel gamePanel = GuiManager.getGamePanel();

        SidePanel sidePanel = GuiManager.getSidePanel();
        add(sidePanel, BorderLayout.CENTER);
        add(new JScrollPane(gamePanel), BorderLayout.WEST);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
