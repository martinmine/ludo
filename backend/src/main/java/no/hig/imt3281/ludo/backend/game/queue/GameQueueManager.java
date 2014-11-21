package no.hig.imt3281.ludo.backend.game.queue;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import no.hig.imt3281.ludo.backend.game.Game;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Martin on 17.11.2014.
 */
public class GameQueueManager {
    private Queue<User> gameQueue;
    private AtomicInteger challengeCounter;
    private QueuedMap<Integer, GameChallenge> challenges;

    public GameQueueManager() {
        this.gameQueue = new ConcurrentLinkedQueue<>();
        this.challenges = new QueuedMap<>(new ConcurrentHashMap<>());
        this.challengeCounter = new AtomicInteger();
    }

    public void enterQueue(User user) {
        this.gameQueue.add(user);
    }

    public void cycle() {
        while (gameQueue.size() >= Game.PLAYERS_MAX) {
            Game game = ServerEnvironment.getGameManager().createGame();
            for (int i = 0; i < Game.PLAYERS_MAX; i++) {
                game.enter(gameQueue.remove());
            }
        }

        challenges.requestForeach((challengeId, challenge) -> challenge.cycle());
    }

    public void challengeUsers(List<User> users) {
        assert(users.size() <= 4);
        int challengeId = this.challengeCounter.incrementAndGet();
        GameChallenge challenge = new GameChallenge(challengeId, ServerEnvironment.getCurrentTimeStamp());
        users.forEach(challenge::challengeUser);

        this.challenges.addItem(challengeId, challenge);
    }

    public GameChallenge getChallenge(final int id) {
        return this.challenges.get(id);
    }
}
