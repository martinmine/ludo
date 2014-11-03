package no.hig.imt3281.ludo.client.chat;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 03.11.2014.
 */
public class TabbedChatContainer extends JTabbedPane {

    public TabbedChatContainer() {

        setPreferredSize(new Dimension(384, 430));
        addTab("Game chat", new GameChatChannel());
        addTab("Global chat", new GlobalChatChannel());
    }
}
