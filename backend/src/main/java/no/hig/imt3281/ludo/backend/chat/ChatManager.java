package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import no.hig.imt3281.ludo.messaging.Message;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages all on-going chats in the system
 */
public class ChatManager {
    private AtomicInteger groupChatIdCounter;
    private Map<String, Integer> groupIdMap;
    private QueuedMap<Integer, GroupChat> groupChats;
    private QueuedMap<Integer, GameChat> gameChats;

    /**
     * Prepares the chat manager
     */
    public ChatManager() {
        this.groupChatIdCounter = new AtomicInteger();
        this.groupIdMap = new HashMap<>();
        this.groupChats = new QueuedMap<>(new HashMap<>());
        this.gameChats = new QueuedMap<>(new HashMap<>());
    }

    /**
     * Cycles pending events in internal data structures
     */
    public void onCycle() {
        this.groupChats.requestForeach((id, room) -> room.onCycle());
        this.groupChats.onCycle();
        this.gameChats.onCycle();
    }

    /**
     * Identifies if a chat room exists or not
     * @param caption Name of the chat room
     * @return True if exists, otherwise false
     */
    public boolean chatRoomExists(String caption) {
        return groupIdMap.containsKey(caption);
    }

    /**
     * Gets a group chat
     * @param caption Name of the group chat
     * @return The group chat
     */
    public GroupChat getGroupChat(String caption) {
        Integer chatRoomId = this.groupIdMap.get(caption);
        if (chatRoomId == null) {
            return null;
        }

        return groupChats.get(chatRoomId);
    }

    /**
     * Gets a group chat
     * @param groupId Id of the group chat
     * @return The group chat
     */
    public GroupChat getGroupChat(int groupId) {
        return groupChats.get(groupId);
    }

    /**
     * Creates a group chat
     * @param caption Name of the group chat to be created
     * @return The new group chat
     */
    public synchronized GroupChat createGroupChat(String caption) {
        Integer id = groupChatIdCounter.incrementAndGet();
        this.groupIdMap.put(caption, id);

        GroupChat room = new GroupChat(id, caption);

        this.groupChats.addItem(id, room);
        return room;
    }

    /**
     * Creates a game chat for a game
     * @return a new Game chat
     */
    public GameChat createGameChat() {
        // TODO
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Gets a game chat
     * @param gameChatId Game Id
     * @return The game chat
     */
    public GameChat getGameChat(int gameChatId) {
        return this.gameChats.get(gameChatId);
    }

    /**
     * Broadcasts a message to the global chat
     * @param message Message to broadcast
     */
    public void broadcastGlobalMessage(Message message) {
        ServerEnvironment.getUserManager().broadcastMessage(message);
    }

    /**
     * Removes a user from all chat rooms which he/she is a part of
     * @param userId User-ID of the user to remove
     */
    public void removeFromChatRooms(final int userId) {
        this.groupChats.requestForeach((groupChatId, groupChat) -> groupChat.leave(userId));
    }

    public void storeChatLogEntry(ChatLogEntry entry) {
        Session session = ServerEnvironment.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            int chatId = (Integer) session.save(entry);
            entry.setId(chatId);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
