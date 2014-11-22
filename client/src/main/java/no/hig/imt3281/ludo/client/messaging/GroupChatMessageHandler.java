package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.chat.ChatRooms;
import no.hig.imt3281.ludo.client.gui.chat.GroupChatChannel;
import no.hig.imt3281.ludo.messaging.GroupChatMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type GroupchatMessage
 */
public class GroupChatMessageHandler implements MessageHandler {
    public void handle(GroupChatMessage message, CommunicationContext context) {
        GroupChatChannel channel = (GroupChatChannel) ChatRooms.getInstance().getChannel(message.getChannelId());
        channel.appendIncomingMessage(
        message.getUsername() + ": " +
        message.getMessage());
    }
}
