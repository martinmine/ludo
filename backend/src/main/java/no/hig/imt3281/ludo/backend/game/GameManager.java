package no.hig.imt3281.ludo.backend.game;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.collections.QueuedMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Game manager keeps track of all the active games and is responsible
 * for destroying and creating games.
 */
public class GameManager {
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getSimpleName());
    private QueuedMap<Integer, Game> games;
    private AtomicInteger gameCounter;

    /**
     * Prepares a new game manager
     */
    public GameManager() {
        this.games = new QueuedMap<>(new ConcurrentHashMap<>());
        this.gameCounter = new AtomicInteger();
    }

    /**
     * Makes a new game
     * @return A new game.
     */
    public Game createGame() {
        int gameId = this.gameCounter.incrementAndGet();
        Game game = new Game(gameId);
        ServerEnvironment.getChatManager().createGameChat(game);

        this.games.addItem(gameId, game);
        return game;
    }

    /**
     * Gets a game by id
     * @param gameId Id of the game
     * @return The game being requested, null if the game doesn't exist.
     */
    public Game getGame(int gameId) {
        return this.games.get(gameId);
    }

    /**
     * Cycles the game manager, its internal resources and the active games.
     */
    public void onCycle() {
        this.games.requestForeach((gameId, game) -> game.cycle());
        this.games.onCycle();
    }

    /**
     * Removes a game from the active game list.
     * @param gameId Id of the game being removed.
     */
    public void reportGameDestroyed(int gameId) {
        this.games.removeItem(gameId);
    }
}
