package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.chat.ChatRooms;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.UserEnteredGameMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;


/**
 * Handles incoming messages of the type UserEnteredGameMessage
 */
public class UserEnteredGameMessageHandler implements MessageHandler {
    public void handle(UserEnteredGameMessage message, CommunicationContext context) {
        message.getPlayers().forEach(playerId -> GuiManager.getGamePanel().joinTable(playerId));

        ChatRooms.getInstance().joinGameChannel(message.getGameId());

    }
}
