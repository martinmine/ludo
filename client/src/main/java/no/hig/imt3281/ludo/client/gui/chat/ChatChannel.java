package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 03.11.2014.
 */
public abstract class ChatChannel extends JPanel{
    private int id;
    private String channelName;
    private int type;
    private ImageIcon icon;
    private JScrollPane chatScrollPane;
    private JTextArea chatMessageContainer;
    private JTextField messageInputField;

    public ChatChannel(int id, String channelName, String welcomeMsg, String iconURL, int type) {
        this.id = id;
        this.type = type;
        this.channelName = channelName;
        this.icon = createImageIcon(iconURL);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        this.chatMessageContainer = new JTextArea(welcomeMsg);
        this.chatMessageContainer.setEditable(false);
        this.chatMessageContainer.setWrapStyleWord(true);
        this.chatMessageContainer.setLineWrap(true);

        this.chatScrollPane = new JScrollPane(chatMessageContainer);

        this.messageInputField = new JTextField();

        add(this.chatScrollPane);
        add(this.messageInputField, BorderLayout.SOUTH);

        messageInputField.addActionListener(e -> {
            String text = messageInputField.getText();
            if (!text.isEmpty()) {
                ChatMessageHandler.getInstance().broadcastMessage(id, text);
            }
            messageInputField.selectAll();
            messageInputField.setText("");
        });
    }

    public void appendIncomingMessage(String message) {
        chatMessageContainer.append("\n" + message);
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

    public ImageIcon getIcon() {
        return this.icon;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public int getType() {
        return this.type;
    }
}
