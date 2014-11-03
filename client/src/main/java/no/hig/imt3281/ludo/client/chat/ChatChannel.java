package no.hig.imt3281.ludo.client.chat;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 03.11.2014.
 */
public abstract class ChatChannel extends JPanel{
    private JTextArea chatMessageContainer;
    private JTextField messageInputField;

    ChatChannel() {

        this.chatMessageContainer = new JTextArea();
        this.messageInputField = new JTextField();

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        add(chatMessageContainer, BorderLayout.NORTH);
        add(messageInputField, BorderLayout.SOUTH);
    }
}
