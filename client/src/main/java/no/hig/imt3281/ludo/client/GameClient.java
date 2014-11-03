package no.hig.imt3281.ludo.client;

import no.hig.imt3281.ludo.client.chat.ChatPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 27.10.2014.
 */
public class GameClient extends JFrame {
    public GameClient() {
        super("Ludo client");
        setSize(new Dimension(1024, 640));
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        GamePanel gamePanel = new GamePanel();
        ChatPanel chatPanel = new ChatPanel();
        add(chatPanel, BorderLayout.EAST);
        add(gamePanel, BorderLayout.WEST);


        setVisible(true);
    }
}
