package no.hig.imt3281.ludo.backend.game;

import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.GameStartedMessage;
import no.hig.imt3281.ludo.messaging.Message;
import no.hig.imt3281.ludo.messaging.TriggerDiceResult;
import no.hig.imt3281.ludo.messaging.UserEnteredGameMessage;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Martin on 17.11.2014.
 *
 */
public class Game {
    public static final int PLAYERS_MAX = 4;
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    private static final Random RANDOM = new Random();
    private static final int DICE_MAX = 6;

    private int gameId;
    private User[] users;
    private int userCount;
    /**
     * Index of the current turn player
     */
    private int currentMovingPlayer;
    /**
     * User id of the current turn player
     */
    private int currentTurnUserId;
    /**
     * Value of the dice
     */
    private int diceValue;

    public Game(final int gameId) {
        this.gameId = gameId;
        this.users = new User[PLAYERS_MAX];
        this.currentMovingPlayer = -1;
    }

    /**
     * Gets the user id of the user which makes the current turn in the game
     * @return user id
     */
    public int getCurrentTurnUserId() {
        return currentTurnUserId;
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
        broadcastMessage(startMessage);
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

    /**
     * Checks if the current player which has the turn can move any tokens from the current dice value.
     * @return True if player can move anything, otherwise
     */
    private boolean movesAvailable() {
        // TODO: Not yet implemented
        return true;
    }

    private void nextPlayerTurn() {
        final int playerId = this.currentMovingPlayer + 1 % this.userCount;
        LOGGER.info("Next player id is " + playerId);

        this.currentMovingPlayer = playerId;
        this.currentTurnUserId = users[playerId].getId();
    }

    /**
     * Triggers the dice for the current user which has the turn in the game
     */
    public void triggerDice() {
        this.diceValue = RANDOM.nextInt(DICE_MAX) + 1;
        boolean movesAvailable = movesAvailable();

        TriggerDiceResult message = new TriggerDiceResult();
        message.setDiceValue(diceValue);
        message.setAnyTokensValid(movesAvailable);
        broadcastMessage(message);

        if (!movesAvailable) {
            nextPlayerTurn();
        }
    }
}
