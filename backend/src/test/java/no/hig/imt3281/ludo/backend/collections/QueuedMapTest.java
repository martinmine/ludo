package no.hig.imt3281.ludo.backend.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Tests the queued map
 */
public class QueuedMapTest {
    private static final int KEY = 12;
    private static final int VALUE = 22;
    private boolean executed = false;

    private QueuedMap<Integer, Integer> map;

    @Before
    public void setUp() {
        map = new QueuedMap<>(new HashMap<>());
    }

    @Test
    public void testSetRemovedObjectEvent() {
        QueuedMap<Integer, Integer> c = new QueuedMap<>(new HashMap<>());
        c.setRemovedObjectEvent((key, value) -> executed = true);

        c.addItem(KEY, VALUE);
        c.onCycle();
        c.removeItem(KEY);
        c.onCycle();

        assertTrue(executed);
    }

    @Test
    public void testRemoveItem() {
        map.addItem(KEY, VALUE);
        map.onCycle();
        assertTrue(map.containsKey(KEY));
        map.removeItem(KEY);
        map.onCycle();
        assertTrue(!map.containsKey(KEY));
    }

    @Test
    public void testGet() {
        map.addItem(KEY, VALUE);
        map.onCycle();
        int val = map.get(KEY);
        assertEquals(val, VALUE);
    }
}