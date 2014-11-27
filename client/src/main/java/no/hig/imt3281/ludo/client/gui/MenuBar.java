package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.challenge.ChallengeUserFrame;
import no.hig.imt3281.ludo.messaging.CreateChatRoomRequest;
import no.hig.imt3281.ludo.messaging.EnterGameQueueMessage;
import no.hig.imt3281.ludo.messaging.ListChallengeableUsersRequest;

import javax.swing.*;
import java.io.IOException;

/**
 * Class containing the menubar and its sub items
 */
public class MenuBar extends JMenuBar {
    /**
     * Create a new instance of the menu bar
     */
    public MenuBar() {
        // General options
        JMenu fileMenu = new JMenu(Main.getResourceBundle().getString("MENUBAR_TITLE_NAME_FILE"));
        fileMenu.setMnemonic("F".charAt(0));

        // Chat related options
        JMenu chatMenu = new JMenu(Main.getResourceBundle().getString("MENUBAR_TITLE_NAME_CHAT"));
        chatMenu.setMnemonic("C".charAt(0));

        // Game related options
        JMenu gameMenu = new JMenu(Main.getResourceBundle().getString("MENUBAR_TITLE_NAME_GAME"));
        gameMenu.setMnemonic("G".charAt(0));

        // Quit game menu item
        JMenuItem quitGame = new JMenuItem(Main.getResourceBundle().getString("MENUBAR_QUIT_GAME"));
        fileMenu.add(quitGame);
        quitGame.addActionListener(e -> GuiManager.exit());

        // Join new chatroom menu item
        JMenuItem newChatroom = new JMenuItem(Main.getResourceBundle().getString("MENUBAR_JOIN_CHATROOM"));
        chatMenu.add(newChatroom);
        newChatroom.addActionListener(e -> {
            String dialogResult = JOptionPane.showInputDialog
                    (Main.getResourceBundle().getString("MENUBAR_CHATROOM_TITLE_INPUT_MSG"));

            while (dialogResult.isEmpty()) {
                dialogResult = JOptionPane.showInputDialog
                        (Main.getResourceBundle().getString("MENUBAR_CHATROOM_TITLE_NEW_INPUT_MSG"));
            }

            CreateChatRoomRequest request = new CreateChatRoomRequest();
            request.setChatroomName(dialogResult);

            try {
                Main.getServerConnection().sendMessage(request);
            } catch (IOException ex) {
                Main.getServerConnection().close(ex);
            }
        });
        // Challenge players menu item
        JMenuItem challengePlayers = new JMenuItem(Main.getResourceBundle().getString("MENUBAR_CHALLENGE_USERS"));
        gameMenu.add(challengePlayers);
        challengePlayers.addActionListener(e -> {
            ChallengeUserFrame listFrame = ChallengeUserFrame.getInstance();
            listFrame.display();

            try {
                Main.getServerConnection().sendMessage(new ListChallengeableUsersRequest());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,Main.getResourceBundle().getString("COULD_NOT_CONNECT_TO_SERVER"));
                Main.getServerConnection().close(ex);
            }
        });
        // Join random game menu item
        JMenuItem joinRandomGame = new JMenuItem(Main.getResourceBundle().getString("MENUBAR_JOIN_RANDOM_GAME"));
        gameMenu.add(joinRandomGame);
        joinRandomGame.addActionListener(e -> {
            GuiManager.getGamePanel().setLoading(true);

            try {
                Main.getServerConnection().sendMessage(new EnterGameQueueMessage());
            } catch (IOException ex) {
                Main.getServerConnection().close(ex);
            }
        });
        add(fileMenu);
        add(chatMenu);
        add(gameMenu);
    }
}
