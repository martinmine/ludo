package no.hig.imt3281.ludo.client.gui.layer;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.layer.game.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 27.10.2014.
 *
 */
public class Client extends JFrame {

    public MenuBar menuBar;

    public Client() {
        super(Main.resourceBundle.getString("NAME_OF_THE_GAME"));
        setSize(new Dimension(1024, 768));

        menuBar = new MenuBar();

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        GamePanel gamePanel = new GamePanel();
        SidePanel sidePanel = new SidePanel();
        add(sidePanel, BorderLayout.EAST);
        add(new JScrollPane(gamePanel), BorderLayout.WEST);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
