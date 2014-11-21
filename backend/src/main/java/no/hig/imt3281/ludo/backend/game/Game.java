package no.hig.imt3281.ludo.backend.game;

import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.UserEnteredGameMessage;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Martin on 17.11.2014.
 *
 */
public class Game {
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
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

        try {
            user.setTokensOnBoard();
        } catch (IOException e) {
            LOGGER.severe("Joining game failed");
        }

        users[userCount++] = user;
    }

    public void leave(User user) {

    }

    public void cycle() {

    }

    public void start() {
        UserEnteredGameMessage message = new UserEnteredGameMessage();

        message.setGameId(this.gameId);
        message.setPlayerCount(this.userCount);

        for (int i = 0; i < userCount; i++) {
            message.addPlayerId(users[i].getId());
        }

        for (int i = 0; i < userCount; i++) {
            try {
                users[i].getClientConnection().sendMessage(message);
            } catch (IOException e) {
                users[i].getClientConnection().close();
            }
        }
    }

    public void getTargetPosition() {

    }

}
