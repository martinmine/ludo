package no.hig.imt3281.ludo.backend.game;

import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.GameStartedMessage;
import no.hig.imt3281.ludo.messaging.Message;
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
    private int currentMovingPlayer;

    public Game(final int gameId) {
        this.gameId = gameId;
        this.users = new User[PLAYERS_MAX];
    }

    public int getGameId() {
        return gameId;
    }

    public void enter(User user) {
        final int playerId = userCount++;
        user.setCurrentGameId(this.gameId);
        user.setGamePlayerId(playerId);

        try {
            user.setTokensOnBoard();
        } catch (IOException e) {
            LOGGER.severe("Joining game failed");
        }

        users[playerId] = user;
    }

    public void leave(User user) {

    }

    public void cycle() {
        // check if the current player that has to roll has not timed out
    }

    private void nextPlayerMove() {

    }

    public void start() {
        UserEnteredGameMessage message = new UserEnteredGameMessage();

        message.setGameId(this.gameId);
        message.setPlayerCount(this.userCount);

        for (int i = 0; i < userCount; i++) {
            message.addPlayerId(i);
        }

        broadcastMessage(message);

        GameStartedMessage startMessage = new GameStartedMessage();
        for (int i = 0; i < userCount; i++) {
            startMessage.setFaction(i);
        }
    }

    private void sendMessage(User user, Message message) {
        if (user.getCurrentGameId() == this.gameId) {
            try {
                user.getClientConnection().sendMessage(message);
            } catch (IOException e) {
                user.getClientConnection().close();
            }
        }
    }

    public void broadcastMessage(Message message) {
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
