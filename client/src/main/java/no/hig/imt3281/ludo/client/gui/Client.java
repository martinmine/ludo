package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.game.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 27.10.2014.
 *
 */
public class Client extends JFrame {
    public no.hig.imt3281.ludo.client.gui.MenuBar menuBar;
    public Client() {
        super(Main.resourceBundle.getString("NAME_OF_THE_GAME"));

        StartDialog lg = new StartDialog(this);
        lg.setVisible(true);
        setSize(new Dimension(1024, 768));

        menuBar = new no.hig.imt3281.ludo.client.gui.MenuBar();

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        GuiManager.initialize();
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
