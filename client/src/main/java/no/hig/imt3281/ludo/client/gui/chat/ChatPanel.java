package no.hig.imt3281.ludo.client.gui.chat;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 03.11.2014.
 */
public class ChatPanel extends JPanel {
    private TabbedChatContainer tabbedChatContainer;

    public ChatPanel() {
        BorderLayout layout = new BorderLayout();
        setMinimumSize(new Dimension(384,440));
        setBackground(Color.RED);
        setLayout(layout);

        tabbedChatContainer = new TabbedChatContainer();
        add(tabbedChatContainer, BorderLayout.SOUTH);
    }
    public void joinNewChatroom(String channelName) {
        this.tabbedChatContainer.addGroupChatChannel(channelName);
    }
}
