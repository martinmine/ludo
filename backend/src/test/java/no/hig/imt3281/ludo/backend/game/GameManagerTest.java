package no.hig.imt3281.ludo.backend.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for testing the game manager
 */
public class GameManagerTest {
    private GameManager gameManager;

    @Before
    public void setUp() {
        this.gameManager = new GameManager();
    }

    @Test
    public void testGetGame() throws Exception {
        Game game = gameManager.createGame();
        gameManager.onCycle();
        Game fetchedGame = gameManager.getGame(game.getGameId());
        assertEquals(game, fetchedGame);
    }

    @Test
    public void testReportGameDestroyed() throws Exception {
        Game game = gameManager.createGame();

        gameManager.onCycle();
        gameManager.reportGameDestroyed(game.getGameId());
        gameManager.onCycle();

        assertNull(gameManager.getGame(game.getGameId()));
    }

    @Test
    public void testCreateGame() throws Exception {
        Game firstGame = gameManager.createGame();
        Game secondsGame = gameManager.createGame();

        assertNotNull(firstGame);
        assertNotNull(secondsGame);
        assertEquals(firstGame.getGameId() + 1, secondsGame.getGameId());
    }
}