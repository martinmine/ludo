package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import no.hig.imt3281.ludo.messaging.ChatMessage;
import no.hig.imt3281.ludo.messaging.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public abstract class ChatRoom {
    private QueuedMap<Integer, User> users;
    private static final Logger LOGGER = Logger.getLogger(ChatRoom.class.getSimpleName());

    public ChatRoom() {
        this.users = new QueuedMap<>(new HashMap<>());
        this.users.setAddedObjectEvent((userId, user) ->
                broadcastSystemMessage(ChatMessage.USER_JOIN + user.getUsername()));
        this.users.setRemovedObjectEvent((userId, user) ->
                broadcastSystemMessage(ChatMessage.USER_LEAVE + user.getUsername()));
    }

    public void join(User user) {
        this.users.addItem(user.getId(), user);
    }

    public void leave(int user) {
        LOGGER.info("User " + user + " leaving chat");
        this.users.removeItem(user);
    }

    public void broadcastMessage(final Message message) {
        users.requestForeach((userId, user) -> {
            if (user.getClientConnection() != null) {
                try {
                    user.getClientConnection().sendMessage(message);
                } catch (IOException e) {
                    LOGGER.log(Level.INFO, e.getMessage(), e);
                    user.getClientConnection().close();
                }
            }
        });
    }

    public abstract void userSays(User user, String chatMessage);
    public abstract void broadcastSystemMessage(String systemMessage);

    public void onCycle() {
        this.users.onCycle();
    }
}
