package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;
import no.hig.imt3281.ludo.client.chat.ChatRooms;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Joakim on 03.11.2014.
 */
public class TabbedChatContainer extends JTabbedPane {
    public TabbedChatContainer() {
        setPreferredSize(new Dimension(384, 430));

        //addTab(Main.resourceBundle.getString("GAME_CHAT_CHANNEL"), gameChatIcon, new GameChatChannel());

        ChatChannel globalChat = ChatRooms.getInstance().getChannel(-1);
        addTab(globalChat.getChannelName(), globalChat.getIcon(), globalChat);
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

    public void addGroupChatChannel(ChatChannel channel){
        addTab(channel.getChannelName(), channel.getIcon(), channel);
    }
}
