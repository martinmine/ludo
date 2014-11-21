package no.hig.imt3281.ludo.backend.collections;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * An asynchronous collection for handling storing of items.
 * The idea in this collection is that any threads can access specific items, while modifying
 * the content of the collection (adding and removing) is done asynchronously.
 * Only one thread is allowed to modify the collection, which is the thread performing the
 * modifications to the collection. Foreach loops are also done asynchronously. Every request
 * to either add, remove or foreach the collections are added to a queue. These requests are
 * then handled by the writer thread.
 */
public class QueuedMap<K, V> {
    private Map<K, V> map;

    private Queue<KeyValuePair<K, V>> addQueue;
    private Queue<K> removeQueue;
    private Queue<QueuedAction<K, V>> actionQueue;

    private QueuedAction<K, V> addedObjectEvent;
    private QueuedAction<K, V> removedObjectEvent;

    /**
     * Prepares a new queued map for usage
     * @param map The internal data structure for the map. Should be thread-safe.
     */
    public QueuedMap(Map<K, V> map) {
        this.addQueue = new ConcurrentLinkedQueue<>();
        this.removeQueue = new ConcurrentLinkedQueue<>();
        this.actionQueue = new ConcurrentLinkedQueue<>();
        this.map = map;
    }

    /**
     * Sets the event to be triggered when items are removed from the collection
     * @param addedObjectEvent Object being added
     */
    public void setAddedObjectEvent(QueuedAction<K, V> addedObjectEvent) {
        this.addedObjectEvent = addedObjectEvent;
    }

    /**
     * Sets the event to be triggered when items are removed from the collection
     * @param removedObjectEvent Object being removed
     */
    public void setRemovedObjectEvent(QueuedAction<K, V> removedObjectEvent) {
        this.removedObjectEvent = removedObjectEvent;
    }

    /**
     * Asynchronously adds an item to the collection
     * @param key Key
     * @param value Value
     */
    public void addItem(K key, V value) {
        this.addQueue.add(new KeyValuePair<>(key, value));
    }

    /**
     * Asynchronously removes an item from the collection
     * @param key Key of the item to be removed
     */
    public void removeItem(K key) {
        this.removeQueue.add(key);
    }

    /**
     * Gets a value from the map
     * @param key Key or id
     * @return The value, or null if the key is not in the map
     */
    public V get(K key) {
        return this.map.get(key);
    }

    /**
     * Requests that an action should be executed on every item in the collection
     * @param action The action to perform on the items in the map
     */
    public void requestForeach(QueuedAction<K, V> action) {
        this.actionQueue.add(action);
    }

    /**
     * Performs the queued actions and the asynchronous work that has been queued up through
     * other functions in this class. Only one thread should access this function in order
     * to obtain thread safety.
     */
    public void onCycle() {
        while (!removeQueue.isEmpty()) {
            K removedKey = removeQueue.remove();
            V removedValue = map.remove(removedKey);

            if (removedObjectEvent != null && removedValue != null) {
                removedObjectEvent.performAction(removedKey, removedValue);
            }
        }

        while (!addQueue.isEmpty()) {
            KeyValuePair<K, V> addPair = addQueue.remove();
            map.put(addPair.getKey(), addPair.getValue());

            if (addedObjectEvent != null) {
                addedObjectEvent.performAction(addPair.getKey(), addPair.getValue());
            }
        }

        while (!actionQueue.isEmpty()) {
            QueuedAction<K, V> action = actionQueue.remove();
            for (Map.Entry<K, V> entry : map.entrySet()) {
                action.performAction(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Checks if the collection has a key
     * @param key Key in the collection
     * @return True if the collection has the key, otherwise false.
     */
    public boolean containsKey(K key) {
        return this.map.containsKey(key);
    }
}
