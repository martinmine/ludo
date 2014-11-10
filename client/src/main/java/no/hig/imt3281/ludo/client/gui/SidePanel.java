package no.hig.imt3281.ludo.client.gui;
import no.hig.imt3281.ludo.client.gui.chat.ChatPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 05.11.2014.
 *
 */
public class SidePanel extends JPanel {

    private ChatPanel chatPanel;
    private SideTopPanel sideTopPanel;

    public SidePanel() {

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setMinimumSize(new Dimension(380, 640));

        sideTopPanel = GuiManager.getSideTopPanel();
        chatPanel = GuiManager.getChatPanel() ;
        add(sideTopPanel, BorderLayout.NORTH);
        add(chatPanel, BorderLayout.SOUTH);
    }
}