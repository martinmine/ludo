package no.hig.imt3281.ludo.client.gui.chat;

import javax.swing.*;
import java.awt.*;

/**
 * Panel containing the TabbedChatContainer
 */
public class ChatPanel extends JPanel {
    private TabbedChatContainer tabbedChatContainer;

    public ChatPanel() {
        BorderLayout layout = new BorderLayout();
        setMinimumSize(new Dimension(384,440));
        setLayout(layout);

        tabbedChatContainer = new TabbedChatContainer();
        add(tabbedChatContainer, BorderLayout.SOUTH);
    }

    /**
     * Create a new chatroom in the GUI layer
     * @param channel
     */
    public void joinNewChatroom(ChatChannel channel) {
        this.tabbedChatContainer.addGroupChatChannel(channel);
    }
}
