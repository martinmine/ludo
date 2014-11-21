package no.hig.imt3281.ludo.backend.collections;

/**
 * A key value pair for a collection
 */
public class KeyValuePair<K, V> {
    private K key;
    private V value;

    /**
     * Creates a new key value pair
     * @param key The key
     * @param value The value
     */
    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the key in the key value pair
     * @return Key
     */
    public K getKey() {
        return key;
    }

    /**
     * Gets the value in the key value pair
     * @return Value
     */
    public V getValue() {
        return value;
    }
}
