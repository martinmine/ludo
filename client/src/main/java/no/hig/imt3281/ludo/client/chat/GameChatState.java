package no.hig.imt3281.ludo.client.chat;

/**
 * Created by Joakim on 03.11.2014.
 */
public class GameChatState implements ChatState {
    @Override
    public void broadcastMessage(ChatMessage message) {

        message.setMessageType(ChatMessageBroadcaster.GAME_CHAT);

        // TODO: Send message to server
    }
}
