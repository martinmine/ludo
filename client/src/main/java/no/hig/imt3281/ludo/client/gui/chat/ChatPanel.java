package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.chat.ChatRooms;

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
    public void joinNewChatroom(ChatChannel channel) {
        this.tabbedChatContainer.addGroupChatChannel(channel);
    }
}
