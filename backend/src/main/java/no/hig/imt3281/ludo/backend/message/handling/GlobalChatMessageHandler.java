package no.hig.imt3281.ludo.backend.message.handling;

import com.mysql.fabric.Server;
import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.chat.GameChat;
import no.hig.imt3281.ludo.messaging.GlobalChatMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;

/**
 * Created by Martin on 11.11.2014.
 */
public class GlobalChatMessageHandler {
    public void handle(GlobalChatMessage request, CommunicationContext context) {
        GlobalChatMessage message = new GlobalChatMessage(request.getMessage());
        

        message.setTimestamp(ServerEnvironment.getCurrentTimeStamp());

        ServerEnvironment.getChatManager().broadcastGlobalMessage(message);
    }
}
