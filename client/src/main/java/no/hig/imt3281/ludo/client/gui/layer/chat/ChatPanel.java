package no.hig.imt3281.ludo.client.gui.layer.chat;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 03.11.2014.
 */
public class ChatPanel extends JPanel {
    public ChatPanel() {
        BorderLayout layout = new BorderLayout();
        setPreferredSize(new Dimension(384,640));
        setBackground(Color.RED);
        setLayout(layout);

        TabbedChatContainer tabbedChatContainer = new TabbedChatContainer();
        add(tabbedChatContainer, BorderLayout.SOUTH);


    }
}
