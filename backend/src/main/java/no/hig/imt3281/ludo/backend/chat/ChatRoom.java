package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import no.hig.imt3281.ludo.messaging.Message;

import java.io.IOException;
import java.util.HashMap;

/**
 * A chat room which a user can create
 */
public abstract class ChatRoom {
    private QueuedMap<Integer, User> users;

    public ChatRoom() {
        this.users = new QueuedMap<>(new HashMap<>());
    }

    public void join(User user) {
        this.users.addItem(user.getId(), user);
    }

    public void leave(User user) {
        this.users.removeItem(user.getId());
    }

    public void broadcastMessage(final Message message) {
        users.requestForeach((userId, user) -> {
            if (user.getClientConnection() != null) {
                try {
                    user.getClientConnection().sendMessage(message);
                } catch (IOException e) {
                    user.getClientConnection().close();
                }
            }
        });
    }

    abstract void userSays(User user, String message);

    public void onCycle() {
        this.users.onCycle();
    }
}
