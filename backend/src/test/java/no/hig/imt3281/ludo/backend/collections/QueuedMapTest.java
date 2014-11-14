package no.hig.imt3281.ludo.backend.collections;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class QueuedMapTest {
    private static final int KEY = 12;
    private static final int VALUE = 22;
    private boolean executed = false;

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
}