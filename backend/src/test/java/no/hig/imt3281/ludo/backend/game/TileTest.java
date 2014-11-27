package no.hig.imt3281.ludo.backend.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {

    public static final int RED = 0;
    public static final int BLUE = 1;
    private Tile tester = new Tile();

    Token red0 = new Token(RED, 0, 0);
    Token red1 = new Token(RED, 1, 1);
    Token red2 = new Token(RED, 2, 2);
    Token red3 = new Token(RED, 3, 3);

    Token blue0 = new Token(BLUE, 0, 0);

    @Test
    public void testAddToken() {
        assertTrue(tester.isEmpty());
        tester.addToken(red0);
        assertFalse(tester.isEmpty());
        assertTrue(tester.getBlockSize() == 1);
        tester.addToken(red1);
        tester.addToken(red2);
        assertTrue(tester.getBlockSize() == 3);
        tester.addToken(red3);
        assertTrue(tester.getBlockSize() == 4);
    }

    @Test
    public void testRemove() {
        testAddToken();
        assertFalse(tester.isEmpty());

        red3 = tester.remove();
        assertEquals(red3, this.red3);
        tester.remove();
        red1 = tester.remove();
        assertEquals(red1, this.red1);
        assertTrue(tester.getBlockSize() == 1);
        red0 = tester.addToken(blue0);
        assertEquals(red0, this.red0);
        assertTrue(tester.getBlockSize() == 1);
        blue0 = tester.remove();
        assertEquals(blue0, this.blue0);
        assertTrue(tester.isEmpty());
    }

}