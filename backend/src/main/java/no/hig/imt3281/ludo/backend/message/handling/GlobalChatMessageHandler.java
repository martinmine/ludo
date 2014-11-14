package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.GlobalChatMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 11.11.2014.
 */
public class GlobalChatMessageHandler implements MessageHandler {
    public void handle(GlobalChatMessage request, CommunicationContext context) {
        GlobalChatMessage message = new GlobalChatMessage();
        message.setMessage(request.getMessage());
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());

        message.setTimestamp(ServerEnvironment.getCurrentTimeStamp());
        message.setUsername(user.getUsername());
        message.setUserId(user.getId());
        message.setMessage(request.getMessage());

        ServerEnvironment.getChatManager().broadcastGlobalMessage(message);
    }
}
