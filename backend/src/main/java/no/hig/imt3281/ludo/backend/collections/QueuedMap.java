package no.hig.imt3281.ludo.backend.collections;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Martin on 10.11.2014.
 */
public class QueuedMap<K, V> {
    private Map<K, V> map;

    private Queue<KeyValuePair<K, V>> addQueue;
    private Queue<K> removeQueue;
    private Queue<QueuedAction<K, V>> actionQueue;

    private QueuedAction<K, V> addedObjectEvent;
    private QueuedAction<K, V> removedObjectEvent;

    public QueuedMap(Map<K, V> map) {
        this.addQueue = new ConcurrentLinkedQueue<>();
        this.removeQueue = new ConcurrentLinkedQueue<>();
        this.actionQueue = new ConcurrentLinkedQueue<>();
        this.map = map;
    }

    public void setAddedObjectEvent(QueuedAction<K, V> addedObjectEvent) {
        this.addedObjectEvent = addedObjectEvent;
    }

    public void setRemovedObjectEvent(QueuedAction<K, V> removedObjectEvent) {
        this.removedObjectEvent = removedObjectEvent;
    }

    public void addItem(K key, V value) {
        this.addQueue.add(new KeyValuePair<>(key, value));
    }

    public void removeItem(K key) {
        this.removeQueue.add(key);
    }

    public V get(K key) {
        return this.map.get(key);
    }

    public void requestForeach(QueuedAction<K, V> action) {
        this.actionQueue.add(action);
    }

    public void onCycle() {
        while (removeQueue.size() > 0) {
            K removedKey = removeQueue.remove();
            V removedValue = map.remove(removedKey);

            if (removedObjectEvent != null && removedValue != null) {
                removedObjectEvent.performAction(removedKey, removedValue);
            }
        }

        while (addQueue.size() > 0) {
            KeyValuePair<K, V> addPair = addQueue.remove();
            map.put(addPair.getKey(), addPair.getValue());

            if (addedObjectEvent != null) {
                addedObjectEvent.performAction(addPair.getKey(), addPair.getValue());
            }
        }

        while (actionQueue.size() > 0) {
            QueuedAction<K, V> action = actionQueue.remove();
            for (Map.Entry<K, V> entry : map.entrySet()) {
                action.performAction(entry.getKey(), entry.getValue());
            }
        }
    }

    public boolean containsKey(K key) {
        return this.map.containsKey(key);
    }
}
