package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.chat.GroupChat;
import no.hig.imt3281.ludo.messaging.CreateChatRoomRequest;
import no.hig.imt3281.ludo.messaging.CreateChatRoomResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.io.IOException;

/**
 * Created by Martin on 11.11.2014.
 */
public class CreateChatRoomRequestHandler implements MessageHandler {
    public void handle(CreateChatRoomRequest request, CommunicationContext context) {
        CreateChatRoomResult response = new CreateChatRoomResult();
        GroupChat room;

        if (ServerEnvironment.getChatManager().chatRoomExists(request.getChatroomName())) {
            room = ServerEnvironment.getChatManager().getGroupChat(request.getChatroomName());

            // If someone made a room, but server is not done with making it
            if (room == null) {
                response.setStatus(CreateChatRoomResult.ERROR);
            }
        } else {
            room = ServerEnvironment.getChatManager().createGroupChat(request.getChatroomName());
        }

        if (room != null) {
            response.setStatus(CreateChatRoomResult.OK);
            response.setChannelId(room.getId());
            response.setChannelName(room.getCaption());
        }

        try {
            context.sendMessage(response);
        } catch (IOException e) {
            context.close();
        }
    }
}
