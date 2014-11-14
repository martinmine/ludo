package no.hig.imt3281.ludo.backend.collections;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class QueuedMapTest {
    private boolean executed = false;

    @Test
    public void testSetRemovedObjectEvent() {

        QueuedMap<Integer, Integer> c = new QueuedMap<>(new HashMap<>());
        c.setRemovedObjectEvent((key, value) -> executed = true);

        c.addItem(12, 12);
        c.onCycle();
        c.removeItem(12);
        c.onCycle();

        assertTrue(executed);
    }
}