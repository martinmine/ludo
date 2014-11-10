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

    private QueuedAction<K, V> addedObjectEvent;
    private QueuedAction<K, V> removedObjectEvent;

    public QueuedMap(Map<K, V> map) {
        this.addQueue = new ConcurrentLinkedQueue<>();
        this.removeQueue = new ConcurrentLinkedQueue<>();
        this.map = map;
    }

    public void setAddedObjectEvent(QueuedAction<K, V> addedObjectEvent) {
        this.addedObjectEvent = addedObjectEvent;
    }

    public void setRemovedObjectEvent(QueuedAction<K, V> removedObjectEvent) {
        this.removedObjectEvent = removedObjectEvent;
    }

    public void addItem(K key, V value) {
        this.addQueue.add(new KeyValuePair<K, V>(key, value));
    }

    public void removeItem(K key) {
        this.removeQueue.add(key);
    }

    public V get(K key) {
        return this.map.get(key);
    }

    public void onCycle() {
        K removedKey;
        while ((removedKey = removeQueue.remove()) != null) {
            V removedValue = map.remove(removedKey);

            if (removedObjectEvent != null && removedValue != null) {
                removedObjectEvent.performAction(removedKey, removedValue);
            }
        }

        KeyValuePair<K, V> addPair;
        while ((addPair = addQueue.remove()) != null) {
            map.put(addPair.getKey(), addPair.getValue());

            if (addedObjectEvent != null) {
                addedObjectEvent.performAction(addPair.getKey(), addPair.getValue());
            }
        }
    }

    public boolean containsKey(K key) {
        return this.map.containsKey(key);
    }
}
