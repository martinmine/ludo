package no.hig.imt3281.ludo.client.gui.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    public static final int FIRST_BASE_TILE = 0;
    public static final int SECOND_BASE_TILE = 1;
    public static final int GOAL_TILE = 62;
    public static final int FIRST_TOKEN = 0;
    public static final int SECOND_TOKEN = 1;
    private Player redPlayer;
    private Player bluePlayer;

    @Test
    public void testGetTileIndex() {
        redPlayer = new Player(Faction.RED);
        bluePlayer = new Player(Faction.BLUE);

        assertEquals(76, redPlayer.getTileIndex(FIRST_BASE_TILE));
        assertEquals(57, redPlayer.getTileIndex(GOAL_TILE));
        assertEquals(80, bluePlayer.getTileIndex(FIRST_BASE_TILE));
        assertEquals(81, bluePlayer.getTileIndex(SECOND_BASE_TILE));
    }

    @Test
    public void testGetTokenPosition() {
        redPlayer = new Player(Faction.RED);
        bluePlayer = new Player(Faction.BLUE);

        assertEquals(76, redPlayer.getTokenPosition(FIRST_TOKEN));
        assertEquals(77, redPlayer.getTokenPosition(SECOND_TOKEN));

        assertEquals(80, bluePlayer.getTokenPosition(FIRST_TOKEN));
        assertEquals(81, bluePlayer.getTokenPosition(SECOND_TOKEN));
    }
}