package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.chat.GroupChat;
import no.hig.imt3281.ludo.messaging.GroupChatMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 11.11.2014.
 */
public class GroupChatMessageHandler implements MessageHandler {
    public void handle(GroupChatMessage request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(request.getUserId());
        GroupChat chat = ServerEnvironment.getChatManager().getGroupChat(request.getChannelId());

        if (user != null && chat != null) {
            chat.userSays(user, request.getMessage());
        }
    }
}
