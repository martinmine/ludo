package no.hig.imt3281.ludo.client.gui.layer.chat;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 03.11.2014.
 */
public abstract class ChatChannel extends JPanel{
    private JScrollPane chatScrollPane;
    private JTextArea chatMessageContainer;
    private JTextField messageInputField;

    public ChatChannel(String welcomeMsg) {
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

        chatMessageContainer.append("\n\n");

        messageInputField.addActionListener(e -> {
            String text = messageInputField.getText();
            if (text != "") {
                this.chatMessageContainer.append(text + "\n");
            }
            messageInputField.selectAll();
            messageInputField.setText("");
        });
    }
}
