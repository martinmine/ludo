package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.chat.ChatRooms;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.CreateChatRoomResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * Created by Joakim on 14.11.2014.
 */
public class CreateChatRoomResultHandler implements MessageHandler {
    private static final Logger LOGGER = Logger.getLogger(CreateChatRoomResultHandler.class.getSimpleName());
    public void handle(CreateChatRoomResult result, CommunicationContext context) {
        LOGGER.info("CHATROOMRESPONSE" + result.getStatus());
        if (result.getStatus() == CreateChatRoomResult.OK) {
            ChatRooms.getInstance().joinGroupChannel(result.getChannelId(), result.getChannelName());
        } else {
            JOptionPane.showMessageDialog(null, Main.resourceBundle.getString("MENUBAR_CHATROOM_FAILED_TO_JOIN_CHATROOM") + result.getChannelName());
        }

    }
}