package no.hig.imt3281.ludo.client.chat;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.GroupChatMessage;
import no.hig.imt3281.ludo.messaging.Message;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joakim on 10.11.2014.
 *
 */
public class GroupChatState implements ChatState {

    private static final Logger LOGGER = Logger.getLogger(GroupChatState.class.getName());

    @Override
    public void broadcastMessage(int channelId, String message) {
        try {
            GroupChatMessage groupChatMessage = new GroupChatMessage();
            groupChatMessage.setMessage(message);
            groupChatMessage.setChannelId(channelId);
            Main.getServerConnection().sendMessage(groupChatMessage);
        } catch (IOException e) {
            LOGGER.severe("Failed to broadcast message");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
