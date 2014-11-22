package no.hig.imt3281.ludo.backend.game;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.collections.QueuedMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Created by Martin on 17.11.2014.
 */
public class GameManager {
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getSimpleName());
    private QueuedMap<Integer, Game> games;
    private AtomicInteger gameCounter;

    public GameManager() {
        this.games = new QueuedMap<>(new ConcurrentHashMap<>());
        this.gameCounter = new AtomicInteger();
    }

    public Game createGame() {
        int gameId = this.gameCounter.incrementAndGet();
        Game game = new Game(gameId);
        ServerEnvironment.getChatManager().createGameChat(game);

        this.games.addItem(gameId, game);
        return game;
    }

    public Game getGame(int gameId) {
        LOGGER.info("Requesting game with id " + gameId + " there are " +  this.games.size() + " games");
        return this.games.get(gameId);
    }

    public void cycle() {
        this.games.requestForeach((gameId, game) -> game.cycle());
        this.games.onCycle();
    }
}
