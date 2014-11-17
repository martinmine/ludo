package no.hig.imt3281.ludo.backend.game.queue;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.game.Game;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Martin on 17.11.2014.
 */
public class GameQueueManager {
    private Queue<User> gameQueue;

    public GameQueueManager() {
        this.gameQueue = new ConcurrentLinkedQueue<>();
    }

    public void enterQueue(User user) {
        this.gameQueue.add(user);
    }

    public void cycle() {
        while (gameQueue.size() >= 4) {
            Game game = ServerEnvironment.getGameManager().createGame();
            for (int i = 0; i < Game.PLAYERS_MAX; i++) {
                game.enter(gameQueue.remove());
            }
        }
    }
}
