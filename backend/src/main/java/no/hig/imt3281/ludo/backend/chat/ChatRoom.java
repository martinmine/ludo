package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import no.hig.imt3281.ludo.messaging.ChatMessage;
import no.hig.imt3281.ludo.messaging.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Abstract class for a chat room defining common logic for chat rooms
 */
public abstract class ChatRoom {
    private QueuedMap<Integer, User> users;
    private static final Logger LOGGER = Logger.getLogger(ChatRoom.class.getSimpleName());

    /**
     * Creates a new chat room
     */
    public ChatRoom() {
        this.users = new QueuedMap<>(new HashMap<>());
        this.users.setAddedObjectEvent((userId, user) ->
                broadcastSystemMessage(ChatMessage.USER_JOIN + user.getUsername()));
        this.users.setRemovedObjectEvent((userId, user) ->
                broadcastSystemMessage(ChatMessage.USER_LEAVE + user.getUsername()));
    }

    /**
     * Add a user to the chat room
     * @param user User to add to the chat room
     */
    public void join(User user) {
        this.users.addItem(user.getId(), user);
    }

    /**
     * Make a user exit the chat room
     * @param user User exiting the chat room
     */
    public void leave(int user) {
        LOGGER.info("User " + user + " leaving chat");
        this.users.removeItem(user);
    }

    /**
     * Broadcasts a message to all the participants in the chat room
     * @param message Message to broadcast
     */
    public void broadcastMessage(final Message message) {
        users.requestForeach((userId, user) -> {
            if (user.getClientConnection() != null) {
                try {
                    user.getClientConnection().sendMessage(message);
                } catch (IOException e) {
                    user.getClientConnection().close(e);
                }
            }
        });
    }

    /**
     * Make a user broadcast a message to the room
     * @param user User who sends the message (sender)
     * @param chatMessage Message being sent to the chat room
     */
    public abstract void userSays(User user, String chatMessage);

    /**
     * Broadcasts a system message to the chat room
     * @param systemMessage System message to broadcast
     */
    public abstract void broadcastSystemMessage(String systemMessage);

    /**
     * Cycles the chat room and handles events that needs to be taken care of
     */
    public void onCycle() {
        this.users.onCycle();
    }
}
