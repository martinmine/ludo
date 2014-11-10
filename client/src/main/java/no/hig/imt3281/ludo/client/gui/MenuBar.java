package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;

import javax.swing.*;

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
            String dialogResult = JOptionPane.showInputDialog("DA ROOM?");

            GuiManager.getChatPanel().joinNewChatroom(dialogResult);
        });

        add(fileMenu);
    }
}
