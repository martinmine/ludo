package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.chat.ChatRooms;
import no.hig.imt3281.ludo.client.gui.chat.GameChatChannel;
import no.hig.imt3281.ludo.messaging.GameChatMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type GameChatMessage
 */
public class GameChatMessageHandler implements MessageHandler {
    public void handle(GameChatMessage message, CommunicationContext context) {
        GameChatChannel channel = (GameChatChannel) ChatRooms.getInstance().getChannel(ChatRooms.GAME_CHAT_KEY);
        channel.appendIncomingMessage(
                message.getUsername() + ": " +
                message.getMessage());
    }
}
