package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.chat.GameChat;
import no.hig.imt3281.ludo.messaging.GameChatMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 11.11.2014.
 */
public class GameChatMessageHandler implements MessageHandler {
    public void handle(GameChatMessage request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());
        GameChat chat = ServerEnvironment.getChatManager().getGameChat(user.getCurrentGameId());

        chat.userSays(user, request.getMessage());
    }
}
