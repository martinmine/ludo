package no.hig.imt3281.ludo.client.gui.chat;

import no.hig.imt3281.ludo.client.chat.ChatMessageHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class containing general data for a chatchannel
 */
public abstract class ChatChannel extends JPanel{
    private int id;
    private String channelName;
    private int type;
    private ImageIcon icon;
    private JScrollPane chatScrollPane;
    private JTextArea chatMessageContainer;
    private JTextField messageInputField;

    /**
     * Creates a new instance of ChatChannel
     * @param id for the channel
     * @param channelName to be displayed on the tab
     * @param welcomeMsg to be displayed upon channel entry
     * @param iconURL resource path to the favicon
     * @param type type of channel identifier
     */
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

    /**
     * Appends incoming message
     * @param message to be appended
     */
    public void appendIncomingMessage(String message) {
        chatMessageContainer.append("\n" + message);
        JScrollBar bar = chatScrollPane.getVerticalScrollBar();
        System.out.println(bar.getMaximum());
        bar.setValue(bar.getMaximum());
    }


    /**
     * Creates imageIcon
     *
     * @param path to the resource file
     * @return imageIcon from resource or null
     */
    public  ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedChatContainer.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * @return the chat channels favicon
     */
    public ImageIcon getIcon() {
        return this.icon;
    }

    /**
     * @return name of the chat channel
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * @return type of the chatchannel
     */
    public int getType() {
        return this.type;
    }
}
