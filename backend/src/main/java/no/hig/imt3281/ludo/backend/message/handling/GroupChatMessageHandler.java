package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.chat.GroupChat;
import no.hig.imt3281.ludo.messaging.GroupChatMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.util.logging.Logger;

/**
 * Message handler for messages sent when a user chats in a user generated group chat.
 */
public class GroupChatMessageHandler implements MessageHandler {
    private static final Logger LOGGER = Logger.getLogger(GroupChatMessageHandler.class.getSimpleName());

    public void handle(GroupChatMessage request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());
        GroupChat chat = ServerEnvironment.getChatManager().getGroupChat(request.getChannelId());

        if (user != null && chat != null) {
            LOGGER.info("User " + user.getUsername() + " says " + request.getMessage());
            chat.userSays(user, request.getMessage());
        }
    }
}
