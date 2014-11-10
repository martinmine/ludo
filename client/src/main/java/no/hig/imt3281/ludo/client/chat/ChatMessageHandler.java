package no.hig.imt3281.ludo.client.chat;

/**
 * Created by Joakim on 03.11.2014.
 */
public class ChatMessageHandler {
    public static final int GAME_CHAT = 0;
    public static final int GLOBAL_CHAT = 1;

    private ChatState currentState;
    private ChatMessageHandler() {
        currentState = new GameChatState();
    }

    private static class SingletonHolder {
        private static final ChatMessageHandler INSTANCE = new ChatMessageHandler();
    }

    public static ChatMessageHandler getInstance() {
        return SingletonHolder.INSTANCE;
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