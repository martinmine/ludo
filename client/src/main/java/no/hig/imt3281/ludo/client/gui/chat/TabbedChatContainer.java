package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;

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

        ImageIcon gameChatIcon = createImageIcon("/img/ludo20.gif");
        ImageIcon globalChatIcon = createImageIcon("/img/world20.gif");
        addTab(Main.resourceBundle.getString("GAME_CHAT_CHANNEL"), gameChatIcon, new GameChatChannel());
        addTab(Main.resourceBundle.getString("GLOBAL_CHAT_CHANNEL"),globalChatIcon, new GlobalChatChannel());

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                int index = getSelectedIndex();
                ChatMessageHandler.getInstance().setCurrentState(index);
            }
        };
        addChangeListener(changeListener);
    }

    public void addUserGeneratedChatChannel(String channelName){
        addTab(channelName, new UserGeneratedChatChannel());
    }


    /** Returns an ImageIcon, or null if the path was invalid. */
    public  ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedChatContainer.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
