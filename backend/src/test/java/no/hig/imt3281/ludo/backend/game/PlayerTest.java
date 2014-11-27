package no.hig.imt3281.ludo.backend.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    public static final int FIRST_TOKEN = 0;
    public static final int SECOND_TOKEN = 1;
    public static final int FIRST_SHARED_TILE = 4;
    public static final int RED = 0;
    public static final int BLUE = 1;

    private Player redPlayer;
    private Player bluePlayer;

    @Test
    public void testGetStartOfFinishTileIndex() throws Exception {
        redPlayer = new Player(RED);
        bluePlayer = new Player(BLUE);
        assertEquals(52, redPlayer.getStartOfFinishTileIndex());
        assertEquals(58, bluePlayer.getStartOfFinishTileIndex());
    }

    @Test
    public void testGetTokenMapPosition() throws Exception {
        redPlayer = new Player(RED);
        bluePlayer = new Player(BLUE);
        assertEquals(76, redPlayer.getTokenMapPosition(FIRST_TOKEN));
        assertEquals(81, bluePlayer.getTokenMapPosition(SECOND_TOKEN));
    }

    @Test
    public void testGetTileIndex() throws Exception {
        redPlayer = new Player(RED);
        bluePlayer = new Player(BLUE);
        assertEquals(0, redPlayer.getTileIndex(FIRST_SHARED_TILE));
        assertEquals(13, bluePlayer.getTileIndex(FIRST_SHARED_TILE));
    }
}