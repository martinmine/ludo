package no.hig.imt3281.ludo.backend.chat;

import no.hig.imt3281.ludo.backend.collections.QueuedMap;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Martin on 11.11.2014.
 */
public class ChatManager {
    private AtomicInteger groupChatIdCounter;
    private HashMap<String, Integer> groupIdMap;
    private QueuedMap<Integer, ChatRoom> groupChats;
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

    public ChatRoom getGroupChat(String caption) {
        Integer chatRoomId = this.groupIdMap.get(caption);
        if (chatRoomId == null) {
            return null;
        }

        return groupChats.get(chatRoomId);
    }

    public synchronized ChatRoom createGroupChat(String caption) {
        Integer id = groupChatIdCounter.incrementAndGet();
        this.groupIdMap.put(caption, id);

        ChatRoom room = new GroupChat(id, caption);

        this.groupChats.addItem(id, room);
        return room;
    }
}
