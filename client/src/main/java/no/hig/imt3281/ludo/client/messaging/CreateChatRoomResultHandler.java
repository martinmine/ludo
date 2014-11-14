package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.chat.ChatRooms;
import no.hig.imt3281.ludo.messaging.CreateChatRoomResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Joakim on 14.11.2014.
 */
public class CreateChatRoomResultHandler implements MessageHandler {
    public void handle(CreateChatRoomResult result, CommunicationContext context) {
        if (result.getStatus() == CreateChatRoomResult.OK) {
            ChatRooms.getInstance().joinGroupChannel(result.getChannelId(), result.getChannelName());
        }

    }
}
