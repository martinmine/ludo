package no.hig.imt3281.ludo.backend.game;

import no.hig.imt3281.ludo.backend.User;

/**
 * Created by Martin on 17.11.2014.
 */
public class Game {
    public static final int PLAYERS_MAX = 4;

    private int gameId;
    private User[] users;
    private int userCount;

    public Game(final int gameId) {
        this.gameId = gameId;
        this.users = new User[PLAYERS_MAX];
    }

    public void enter(User user) {
        user.setCurrentGameId(this.gameId);
        user.setGamePlayerId(this.userCount);

        users[userCount++] = user;

        // TODO: Notify user that they entered a game
    }

    public void leave(User user) {

    }

    public void cycle() {

    }

    public void start() {
        // TODO
    }
}
