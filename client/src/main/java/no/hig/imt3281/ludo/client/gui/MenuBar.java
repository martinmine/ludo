package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.CreateChatroomRequest;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Joakim on 05.11.2014.
 */
public class MenuBar extends JMenuBar {
    public MenuBar() {

        JMenu fileMenu = new JMenu(Main.resourceBundle.getString("MENUBAR_TITLE_NAME_FILE"));
        fileMenu.setMnemonic("F".charAt(0));

        JMenuItem newItem = new JMenuItem(Main.resourceBundle.getString("MENUBAR_JOIN_CHATROOM"));
        fileMenu.add(newItem);
        newItem.addActionListener(e -> {
            String dialogResult = JOptionPane.showInputDialog
                    (Main.resourceBundle.getString("MENUBAR_CHATROOM_TITLE_INPUT_MSG"));

            while (dialogResult.isEmpty()) {
                dialogResult = JOptionPane.showInputDialog
                        (Main.resourceBundle.getString("MENUBAR_CHATROOM_TITLE_NEW_INPUT_MSG"));
            }
            CreateChatroomRequest request = new CreateChatroomRequest(dialogResult);
            try {
                Main.getServerConnection().sendMessage(request);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            GuiManager.getChatPanel().joinNewChatroom(dialogResult);
        });

        add(fileMenu);
    }
}
