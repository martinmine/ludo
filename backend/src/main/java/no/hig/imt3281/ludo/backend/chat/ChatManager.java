package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import no.hig.imt3281.ludo.messaging.Message;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Martin on 11.11.2014.
 */
public class ChatManager {
    private AtomicInteger groupChatIdCounter;
    private HashMap<String, Integer> groupIdMap;
    private QueuedMap<Integer, GroupChat> groupChats;
    private QueuedMap<Integer, ChatRoom> gameChats;

    public ChatManager() {
        this.groupChatIdCounter = new AtomicInteger();
        this.groupIdMap = new HashMap<>();
        this.groupChats = new QueuedMap<>(new HashMap<>());
        this.gameChats = new QueuedMap<>(new HashMap<>());
    }

    public void onCycle() {
        this.groupChats.requestForeach((id, room) -> room.onCycle());
        this.groupChats.onCycle();
        this.gameChats.onCycle();
    }

    public boolean chatRoomExists(String caption) {
        return groupIdMap.containsKey(caption);
    }

    public GroupChat getGroupChat(String caption) {
        Integer chatRoomId = this.groupIdMap.get(caption);
        if (chatRoomId == null) {
            return null;
        }

        return groupChats.get(chatRoomId);
    }

    public synchronized GroupChat createGroupChat(String caption) {
        Integer id = groupChatIdCounter.incrementAndGet();
        this.groupIdMap.put(caption, id);

        GroupChat room = new GroupChat(id, caption);

        this.groupChats.addItem(id, room);
        return room;
    }

    public GameChat createGameChat() {
        // TODO
        throw new NotImplementedException();
    }

    public void broadcastGlobalMessage(Message message) {
        ServerEnvironment.getUserManager().broadcastMessage(message);
    }

    public void removeFromChatRooms(final int userId) {
        this.groupChats.requestForeach((groupChatId, groupChat) -> groupChat.leave(userId));
    }
}
