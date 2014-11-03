package no.hig.imt3281.ludo.client.chat;

/**
 * Created by Joakim on 03.11.2014.
 */
public class ChatMessageBroadcaster {
    public static final int GAME_CHAT = 0;
    public static final int GLOBAL_CHAT = 1;

    private ChatState currentState;

    public ChatMessageBroadcaster() {
        currentState = new GameChatState();
    }

    public void setCurrentState(final int stateType) {
        switch(stateType) {
            case GAME_CHAT:
                this.currentState = new GameChatState();
                break;
            case GLOBAL_CHAT:
                this.currentState = new GlobalChatState();
                break;
            default:
                break;
        }
    }

    public void broadcastMessage(ChatMessage message) {
        this.currentState.broadcastMessage(message);
    }
}
