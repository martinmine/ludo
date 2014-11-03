package no.hig.imt3281.ludo.client.chat;

/**
 * Created by Joakim on 03.11.2014.
 */
public class GlobalChatState implements ChatState {
    @Override
    public void broadcastMessage(ChatMessage message) {

        message.setMessageType(ChatMessageBroadcaster.GLOBAL_CHAT);

        // TODO: Send message to server
    }
}
