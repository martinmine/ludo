package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.GameChatMessage;

/**
 * A chat for either an ongoing game or a new game with pending requests
 */
public class GameChat extends ChatRoom {
    @Override
    public void userSays(User user, String chatMessage) {
        GameChatMessage message = new GameChatMessage();
        message.setMessage(chatMessage);
        message.setUsername(user.getUsername());
        message.setUserId(user.getId());
        message.setTimestamp(ServerEnvironment.getCurrentTimeStamp());

        super.broadcastMessage(message);
    }

    @Override
    public void broadcastSystemMessage(String systemMessage) {
        GameChatMessage message = new GameChatMessage();
        message.setMessage(systemMessage);
        message.setTimestamp(ServerEnvironment.getCurrentTimeStamp());

        super.broadcastMessage(message);
    }
}
