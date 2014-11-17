package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.challenge.ChallengeListDialog;
import no.hig.imt3281.ludo.messaging.CreateChatRoomRequest;
import no.hig.imt3281.ludo.messaging.ListChallengeableUsersRequest;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Joakim on 05.11.2014.
 */
public class MenuBar extends JMenuBar {
    public MenuBar() {

        JMenu fileMenu = new JMenu(Main.resourceBundle.getString("MENUBAR_TITLE_NAME_FILE"));
        fileMenu.setMnemonic("F".charAt(0));

        JMenuItem newChatroom = new JMenuItem(Main.resourceBundle.getString("MENUBAR_JOIN_CHATROOM"));
        fileMenu.add(newChatroom);
        newChatroom.addActionListener(e -> {
            String dialogResult = JOptionPane.showInputDialog
                    (Main.resourceBundle.getString("MENUBAR_CHATROOM_TITLE_INPUT_MSG"));

            while (dialogResult.isEmpty()) {
                dialogResult = JOptionPane.showInputDialog
                        (Main.resourceBundle.getString("MENUBAR_CHATROOM_TITLE_NEW_INPUT_MSG"));
            }

            CreateChatRoomRequest request = new CreateChatRoomRequest();
            request.setChatroomName(dialogResult);

            try {
                Main.getServerConnection().sendMessage(request);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        JMenuItem challengePlayers = new JMenuItem(Main.resourceBundle.getString("MENUBAR_CHALLENGE_USERS"));
        challengePlayers.addActionListener(e -> {
            try {
                Main.getServerConnection().sendMessage(new ListChallengeableUsersRequest());
                new ChallengeListDialog();
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null,Main.resourceBundle.getString("COULD_NOT_CONNECT_TO_SERVER"));
            }
        });
        fileMenu.add(challengePlayers);

        add(fileMenu);
    }
}
