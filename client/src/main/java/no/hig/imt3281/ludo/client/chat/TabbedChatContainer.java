package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;

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
        addTab(Main.resourceBundle.getString("GAME_CHAT_CHANNEL"), new GameChatChannel());
        addTab(Main.resourceBundle.getString("GLOBAL_CHAT_CHANNEL"), new GlobalChatChannel());

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                int index = getSelectedIndex();
                ChatMessageBroadcaster.getInstance().setCurrentState(index);
            }
        };
        addChangeListener(changeListener);
    }
}
