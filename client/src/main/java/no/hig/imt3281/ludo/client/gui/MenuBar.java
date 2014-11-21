package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.challenge.ChallengeUserFrame;
import no.hig.imt3281.ludo.messaging.CreateChatRoomRequest;
import no.hig.imt3281.ludo.messaging.EnterGameQueueMessage;
import no.hig.imt3281.ludo.messaging.ListChallengeableUsersRequest;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joakim on 05.11.2014.
 *
 */
public class MenuBar extends JMenuBar {
    private static final Logger LOGGER = Logger.getLogger(MenuBar.class.getName());

    public MenuBar() {

        JMenu fileMenu = new JMenu(Main.resourceBundle.getString("MENUBAR_TITLE_NAME_FILE"));
        fileMenu.setMnemonic("F".charAt(0));

        JMenu chatMenu = new JMenu(Main.resourceBundle.getString("MENUBAR_TITLE_NAME_CHAT"));
        chatMenu.setMnemonic("C".charAt(0));

        JMenu gameMenu = new JMenu(Main.resourceBundle.getString("MENUBAR_TITLE_NAME_GAME"));
        gameMenu.setMnemonic("G".charAt(0));

        JMenuItem quitGame = new JMenuItem(Main.resourceBundle.getString("MENUBAR_QUIT_GAME"));
        fileMenu.add(quitGame);
        quitGame.addActionListener(e -> System.exit(0));

        JMenuItem newChatroom = new JMenuItem(Main.resourceBundle.getString("MENUBAR_JOIN_CHATROOM"));
        chatMenu.add(newChatroom);
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
                LOGGER.severe("SendMessage failed at MenuBar");
                LOGGER.log(Level.SEVERE, e1.getMessage(), e1);
            }
        });

        JMenuItem challengePlayers = new JMenuItem(Main.resourceBundle.getString("MENUBAR_CHALLENGE_USERS"));
        gameMenu.add(challengePlayers);
        challengePlayers.addActionListener(e -> {
            try {
                Main.getServerConnection().sendMessage(new ListChallengeableUsersRequest());
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null,Main.resourceBundle.getString("COULD_NOT_CONNECT_TO_SERVER"));
            }
        });

        JMenuItem joinRandomGame = new JMenuItem(Main.resourceBundle.getString("MENUBAR_JOIN_RANDOM_GAME"));
        gameMenu.add(joinRandomGame);
        joinRandomGame.addActionListener(e -> {
            GuiManager.getGamePanel().setLoading(true);

            try {
                Main.getServerConnection().sendMessage(new EnterGameQueueMessage());
            } catch (IOException e1) {
                LOGGER.info("Could not send message");
            }

        });

        add(fileMenu);
        add(chatMenu);
        add(gameMenu);
    }
}
