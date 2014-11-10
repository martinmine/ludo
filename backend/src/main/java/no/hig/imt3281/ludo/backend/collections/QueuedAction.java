package no.hig.imt3281.ludo.backend.collections;

/**
 * Created by Martin on 10.11.2014.
 */
public interface QueuedAction<T, V> {
    public void performAction(T key, V value);
}
