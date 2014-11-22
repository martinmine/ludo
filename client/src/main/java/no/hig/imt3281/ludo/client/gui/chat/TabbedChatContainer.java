package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;
import no.hig.imt3281.ludo.client.chat.ChatRooms;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Contains all the different GUI representations of chatrooms
 */
public class TabbedChatContainer extends JTabbedPane {
    /**
     * Creates a new instance of the TabbedChatContainer
     */
    public TabbedChatContainer() {
        setPreferredSize(new Dimension(384, 430));
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                int index = getSelectedIndex();
                ChatChannel selectedTabComponent = (ChatChannel) getComponentAt(index);
                ChatMessageHandler.getInstance().setCurrentState(selectedTabComponent.getType());
            }
        };
        addChangeListener(changeListener);
    }

    @Override
    public Component getTabComponentAt(int index) {
        return super.getTabComponentAt(index);
    }

    /**
     * Adds a new user generated chatroom
     * @param channel is the channel to be added
     */
    public void addGroupChatChannel(ChatChannel channel){
        addTab(channel.getChannelName(), channel.getIcon(), channel);
    }
}
