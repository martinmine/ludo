package no.hig.imt3281.ludo.backend.game.queue;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.game.Game;
import no.hig.imt3281.ludo.messaging.GameChallengeResponse;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * A game challenge initiated by a user.
 * Takes care of the actions when users responds to the challenge.
 */
public class GameChallenge {
    private static final Logger LOGGER = Logger.getLogger(GameChallenge.class.getSimpleName());
    private static final int CHALLENGE_TIMEOUT = 30;
    private int id;
    private List<User> challengedUsers;
    private User owner;
    private int[] userStates;
    private int timeCreated;

    /**
     * Makes a new game challenge
     * @param id Id of the game challenge
     * @param timeCreated The timestamp when the challenge was made
     */
    public GameChallenge(final int id, final int timeCreated) {
        this.id = id;
        this.timeCreated = timeCreated;
        this.challengedUsers = new LinkedList<>();
        this.userStates = new int[Game.PLAYERS_MAX];
    }

    /**
     * Adds a user to the game challenge
     * @param user User being challenged
     */
    public void challengeUser(User user) {
        this.challengedUsers.add(user);
        this.userStates[this.challengedUsers.size() - 1] = GameChallengeState.WAITING;
    }

    /**
     * Sets the initiator of the game challenge
     * @param owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Cycles the game challenge and takes care of timed out events, etc.
     */
    public void cycle() {
        if (ServerEnvironment.getCurrentTimeStamp() > this.timeCreated + CHALLENGE_TIMEOUT) {
            LOGGER.info("Challenge timed out");
            LOGGER.info("Timestamp : " + ServerEnvironment.getCurrentTimeStamp() + " made at " + this.timeCreated);
            destroy();
        }
    }

    /**
     * Make a user accept the game challenge
     * @param user User accepting the challenge
     */
    public void accept(User user) {
        this.userStates[challengedUsers.indexOf(user)] = GameChallengeState.ACCEPTED;
        checkState();
    }

    /**
     * Deny the game challenge for a user
     * @param user User denying the challenge
     */
    public void deny(User user) {
        this.userStates[challengedUsers.indexOf(user)] = GameChallengeState.DENIED;
        checkState();
    }

    private void checkState() {
        int waitingUsers = 0;
        int acceptedUsers = 0;

        // Count accepted/waiting users
        for (int i = 0; i < this.challengedUsers.size(); i++) {
            if (this.userStates[i] == GameChallengeState.ACCEPTED) {
                acceptedUsers++;
            }
            else if (this.userStates[i] == GameChallengeState.WAITING) {
                waitingUsers++;
            }
        }

        // all users responded
        if (waitingUsers == 0) {
            if (acceptedUsers >= 1) {
                LOGGER.info("Challenge starting game");
                Game game = ServerEnvironment.getGameManager().createGame();

                for (User user : this.challengedUsers) {
                    if (this.userStates[this.challengedUsers.indexOf(user)] == GameChallengeState.ACCEPTED) {
                        game.enter(user);
                    }
                }

                game.enter(owner);
                game.start();
                ServerEnvironment.getGameQueueManager().disposeChallenge(id);
            } else {
                destroy();
            }
        }
    }

    /**
     * Tells the users that the challenge was rejected for whatever reason
     */
    private void destroy() {
        LOGGER.info("Challenge failed");
        GameChallengeResponse response = new GameChallengeResponse();
        response.setChallengeId(this.id);
        response.setState(GameChallengeResponse.REJECTED);

        for (User user : this.challengedUsers) {
            int state = this.userStates[this.challengedUsers.indexOf(user)];
            if (state == GameChallengeState.ACCEPTED || state == GameChallengeState.WAITING) {
                try {
                    user.getClientConnection().sendMessage(response);
                } catch (IOException e) {
                    user.getClientConnection().close();
                }
            }
        }

        ServerEnvironment.getGameQueueManager().disposeChallenge(this.id);
    }
}
