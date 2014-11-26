package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.chat.GroupChat;
import no.hig.imt3281.ludo.messaging.CreateChatRoomRequest;
import no.hig.imt3281.ludo.messaging.CreateChatRoomResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Message handler for requests when users wants to make their own chat room.
 */
public class CreateChatRoomRequestHandler implements MessageHandler {
    private static final Logger LOGGER = Logger.getLogger(CreateChatRoomRequestHandler.class.getSimpleName());

    public void handle(CreateChatRoomRequest request, CommunicationContext context) {
        CreateChatRoomResult response = new CreateChatRoomResult();
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());
        GroupChat room;

        if (ServerEnvironment.getChatManager().chatRoomExists(request.getChatroomName())) {
            room = ServerEnvironment.getChatManager().getGroupChat(request.getChatroomName());

            // If someone made a room, but server is not done with making it
            if (room == null) {
                LOGGER.info("Chat room " + request.getChatroomName() + " is being made");
                response.setStatus(CreateChatRoomResult.ERROR);
            } else {
                LOGGER.info("Directing user to existing chat room " + request.getChatroomName());
            }
        } else {
            LOGGER.info("Successfully opened existing chat room " + request.getChatroomName());
            room = ServerEnvironment.getChatManager().createGroupChat(request.getChatroomName());
        }

        if (room != null) {
            room.join(user);
            response.setStatus(CreateChatRoomResult.OK);
            response.setChannelId(room.getId());
            response.setChannelName(room.getCaption());
        }

        try {
            context.sendMessage(response);
        } catch (IOException e) {
            context.close(e);
        }
    }
}
