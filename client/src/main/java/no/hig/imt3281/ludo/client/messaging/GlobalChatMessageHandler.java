package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.chat.ChatRooms;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.client.gui.chat.GlobalChatChannel;
import no.hig.imt3281.ludo.messaging.GlobalChatMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Joakim on 11.11.2014.
 */
public class GlobalChatMessageHandler implements MessageHandler {
    public void handle(GlobalChatMessage message, CommunicationContext context) {
        GlobalChatChannel channel = (GlobalChatChannel) ChatRooms.getInstance().getChannel(ChatRooms.GLOBAL_CHAT_KEY);
        channel.appendIncomingMessage(
                message.getUsername() + ": " +
                 message.getMessage());
    }
}
