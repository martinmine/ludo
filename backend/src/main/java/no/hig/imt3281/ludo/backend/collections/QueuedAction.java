package no.hig.imt3281.ludo.backend.collections;

/**
 * Class defining a function that should be executed on items in a collection
 */
public interface QueuedAction<T, V> {
    /**
     * Action being performed on items in a collection
     * @param key Key to perform an action on
     * @param value Value to perform an action on
     */
    public void performAction(T key, V value);
}
