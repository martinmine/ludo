package no.hig.imt3281.ludo.client.gui.layer;

import no.hig.imt3281.ludo.client.gui.layer.chat.ChatPanel;
import no.hig.imt3281.ludo.client.gui.layer.dice.DicePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 05.11.2014.
 */
public class SidePanel extends JPanel {

    private ChatPanel chatPanel;
    private SideTopPanel sideTopPanel;

    public SidePanel() {

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        setPreferredSize(new Dimension(384, 640));

        sideTopPanel = new SideTopPanel();
        chatPanel = new ChatPanel();
        add(sideTopPanel, BorderLayout.NORTH);
        add(chatPanel, BorderLayout.SOUTH);
    }
}
