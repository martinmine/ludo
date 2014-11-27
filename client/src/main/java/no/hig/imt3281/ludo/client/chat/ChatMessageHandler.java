package no.hig.imt3281.ludo.client.chat;

/**
 * Singleton
 *
 * This class is part of a State pattern.
 * The purpose of this class is to hold the
 * current state of the client to server
 * communication channel.
 *
 * The different states are the three output channels from the chat
 * GameChatState
 * GlobalChatState
 * GroupChatsate(user generated chatrooms)
 *
 */
public class ChatMessageHandler {
    public static final int GAME_CHAT = -2;
    public static final int GLOBAL_CHAT = -1;
    public static final int GROUP_CHAT = 0;
    private ChatState currentState;

    /**
     * Creates a new instance of ChatMessageHandler
     * Initiates current state to global chat state
     */
    private ChatMessageHandler() {
        currentState = new GlobalChatState();
    }

    /**
     * Nested class singetonholder
     */
    private static class SingletonHolder {
        private static final ChatMessageHandler INSTANCE = new ChatMessageHandler();
        private SingletonHolder() {}
    }

    /**
     * Returns the object instance of the ChatMessageHandler
     * @return Object Instance
     */
    public static ChatMessageHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     *
     * @param stateType sets the new current state.
     * Accepts the const value representations of the implemented states.
     */
    public void setCurrentState(final int stateType) {
        switch(stateType) {
            case GAME_CHAT:
                this.currentState = new GameChatState();
                break;
            case GLOBAL_CHAT:
                this.currentState = new GlobalChatState();
                break;
            default: this.currentState = new GroupChatState();
                break;
        }
    }

    /**
     *
     * @param channelId is used by the server to
     * respond back to the correct channel
     * @param message is the message string generated by the user
     */
    public void broadcastMessage(int channelId, String message) {
        this.currentState.broadcastMessage(channelId, message);
    }
}
