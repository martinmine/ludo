package no.hig.imt3281.ludo.backend.game.queue;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.collections.QueuedMap;
import no.hig.imt3281.ludo.backend.game.Game;
import no.hig.imt3281.ludo.messaging.GameChallengeMessage;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Class handling the game queues
 */
public class GameQueueManager {
    private static final Logger LOGGER = Logger.getLogger(GameQueueManager.class.getSimpleName());
    private Queue<User> gameQueue;
    private AtomicInteger challengeCounter;
    private QueuedMap<Integer, GameChallenge> challenges;
    private Lock syncRoot;

    /**
     * Prepares a new game queue manager
     */
    public GameQueueManager() {
        this.gameQueue = new ConcurrentLinkedQueue<>();
        this.challenges = new QueuedMap<>(new ConcurrentHashMap<>());
        this.challengeCounter = new AtomicInteger();
        this.syncRoot = new ReentrantLock();
    }

    /**
     * Makes a user enter the game queue
     * @param user User entering the queue
     */
    public void enterQueue(User user) {
        try {
            this.syncRoot.lock();
            this.gameQueue.add(user);
        } finally {
            this.syncRoot.unlock();
        }
    }

    /**
     * Makes a user leave the queue
     * @param user User leaving the queue
     */
    public void leaveQueue(User user) {
        try {
            this.syncRoot.lock();
            this.gameQueue.remove(user);
        } finally {
            this.syncRoot.unlock();
        }
    }

    /**
     * Cycles the game queue and takes care of timeouts, etc.
     */
    public void onCycle() {
        try {
            this.syncRoot.lock();

            while (gameQueue.size() >= Game.PLAYERS_MAX) {
                LOGGER.info("New game from queue");
                Game game = ServerEnvironment.getGameManager().createGame();
                for (int i = 0; i < Game.PLAYERS_MAX; i++) {
                    game.enter(gameQueue.remove());
                }

                game.start();
            }
        } finally {
            this.syncRoot.unlock();
        }

        challenges.requestForeach((challengeId, challenge) -> challenge.cycle());
        challenges.onCycle();
    }

    /**
     * Challenges a list of users
     * @param users Users to challenge
     */
    public void challengeUsers(List<User> users, User owner) {
        assert users.size() <= 4 && !users.isEmpty();
        final int challengeId = this.challengeCounter.incrementAndGet();

        GameChallenge challenge = new GameChallenge(challengeId, ServerEnvironment.getCurrentTimeStamp());
        challenge.setOwner(owner);
        users.forEach(challenge::challengeUser);

        GameChallengeMessage message = new GameChallengeMessage();
        message.setChallengeId(challengeId);
        message.setChallengerUserId(owner.getId());
        message.setChallengerUsername(owner.getUsername());

        users.forEach(user -> {
            try {
                user.getClientConnection().sendMessage(message);
            } catch (IOException e) {
                user.getClientConnection().close(e);
            }
        });

        this.challenges.addItem(challengeId, challenge);
        LOGGER.info("Added new challenge with id " + challengeId);
    }

    /**
     * Gets a challenge
     * @param id Id of the challenge
     * @return the id of the challenge
     */
    public GameChallenge getChallenge(final int id) {
        return this.challenges.get(id);
    }

    public void disposeChallenge(int id) {
        this.challenges.removeItem(id);
    }
}
