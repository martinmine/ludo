package no.hig.imt3281.ludo.backend.game.queue;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.game.Game;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Martin on 17.11.2014.
 */
public class GameChallenge {
    private static final int CHALLENGE_TIMEOUT = 30;
    private int id;
    private List<User> challengedUsers;
    private int[] userStates;
    private int timeCreated;

    public GameChallenge(final int id, final int timeCreated) {
        this.id = id;
        this.timeCreated = timeCreated;
        this.challengedUsers = new LinkedList<>();
        this.userStates = new int[Game.PLAYERS_MAX];
    }

    public void challengeUser(User user) {
        this.challengedUsers.add(user);
        this.userStates[this.challengedUsers.size() - 1] = GameChallengeState.WAITING;

        // TODO: Notify user's client
    }

    public void cycle() {
        if (ServerEnvironment.getCurrentTimeStamp() + CHALLENGE_TIMEOUT > this.timeCreated) {
            destroy();
        }
    }

    public void accept(User user) {
        this.userStates[challengedUsers.indexOf(user)] = GameChallengeState.ACCEPTED;
    }

    public void deny(User user) {
        this.userStates[challengedUsers.indexOf(user)] = GameChallengeState.DENIED;
    }

    private void checkState() {
        int waitingUsers = 0;
        int acceptedUsers = 0;

        for (int i = 0; i < this.challengedUsers.size(); i++) {
            switch (this.userStates[i]) {
                case GameChallengeState.ACCEPTED: {
                    acceptedUsers++;
                    break;
                }
                case GameChallengeState.WAITING: {
                    waitingUsers++;
                    break;
                }
            }
        }

        if (waitingUsers == 0) { // all users responded
            if (acceptedUsers >= 2) {
                Game game = ServerEnvironment.getGameManager().createGame();

                for (User user : this.challengedUsers) {
                    if (this.userStates[this.challengedUsers.indexOf(user)] == GameChallengeState.ACCEPTED) {
                        game.enter(user);
                    }
                }

                game.start();
            } else {
                destroy();
            }
        }
    }

    private void destroy() {

    }
}
