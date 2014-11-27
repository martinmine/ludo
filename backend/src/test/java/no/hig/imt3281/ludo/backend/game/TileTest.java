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
    public void testAddToken() throws Exception {
        System.out.println("Testing add Token to tile");
        System.out.println(".................................");

        assertTrue(tester.isEmpty());
        System.out.println("The tile is empty");
        tester.addToken(red0);
        System.out.println("Added a red token (0)");
        assertFalse(tester.isEmpty());
        System.out.println("The tile is not empty");
        assertTrue(tester.getBlockSize() == 1);
        System.out.println("There is 1 token at the tile");
        tester.addToken(red1);
        System.out.println("Added a red token (1)");
        tester.addToken(red2);
        System.out.println("Added a red token (2)");
        assertTrue(tester.getBlockSize() == 3);
        System.out.println("There is 3 tokens on this tile (0,1,2)");
        tester.addToken(red3);
        System.out.println("Added a red token (3)");
        assertTrue(tester.getBlockSize() == 4);
        System.out.println("There is 4 tokens on this tile (0,1,2,3)");

        System.out.println("A big blockade has been successfully created!");
        System.out.println("");
    }

    @Test
    public void testIsBlocked() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {
        System.out.println("Testing remove tokens from a tile");
        System.out.println(".................................");

        testAddToken();
        System.out.println("Added 4 red tokens to the tile");
        assertFalse(tester.isEmpty());
        System.out.println("The tile is not empty");

        Token red3 = tester.remove();
        assertEquals(red3, this.red3);
        System.out.println("Removing top token (3) from tile");
        tester.remove();
        System.out.println("Removing a token");
        Token red1 = tester.remove();
        assertEquals(red1, this.red1);
        System.out.println("Removing top token (1) from tile");

        assertTrue(tester.getBlockSize() == 1);
        System.out.println("There is one red token on the tile");

        System.out.println("Trying to add a blue token (0)");
        Token red0 = tester.addToken(blue0);
        assertEquals(red0, this.red0);
        System.out.println("Reds token is thrown out of tile");

        assertTrue(tester.getBlockSize() == 1);
        System.out.println("There is still a token on the tile, but now its blue");

        Token blue0 = tester.remove();
        assertEquals(blue0, this.blue0);
        System.out.println("Removed last token on tile");

        assertTrue(tester.isEmpty());
        System.out.println("Tile is empty!");
        System.out.println("");
    }

    @Test
    public void testIsEmpty() throws Exception {

    }

    @Test
    public void testGetFaction() throws Exception {

    }
}