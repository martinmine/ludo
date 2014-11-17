package no.hig.imt3281.ludo.backend.game;

import no.hig.imt3281.ludo.backend.collections.QueuedMap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Martin on 17.11.2014.
 */
public class GameManager {
    private QueuedMap<Integer, Game> games;
    private AtomicInteger gameCounter;

    public GameManager() {
        this.games = new QueuedMap<>(new ConcurrentHashMap<>());
        this.gameCounter = new AtomicInteger();
    }

    public Game createGame() {
        int gameId = this.gameCounter.incrementAndGet();
        Game game = new Game(gameId);

        this.games.addItem(gameId, game);
        return game;
    }

    public void cycle() {
        this.games.requestForeach((gameId, game) -> game.cycle());
        this.games.onCycle();
    }
}
