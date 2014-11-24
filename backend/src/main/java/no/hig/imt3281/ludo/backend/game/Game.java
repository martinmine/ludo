package no.hig.imt3281.ludo.backend.game;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.*;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Martin on 17.11.2014.
 *
 */
public class Game implements GameMapUpdateListener {
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
    private int diceThrownTimestamp;
    private GameMap gameMap;

    public Game(final int gameId) {
        this.gameId = gameId;
        this.users = new User[PLAYERS_MAX];
        this.currentMovingPlayer = -1;
        this.gameMap = new GameMap(this);
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
            gameMap.addTokens(i);
        }
        broadcastMessage(startMessage);

        AssignUserFactionMessage colorAssignMessage = new AssignUserFactionMessage();
        for (int i = 0; i < userCount; i++) {
            colorAssignMessage.setFaction(i);
            sendMessage(users[i], colorAssignMessage);
        }

        nextPlayerTurn();
    }

    private void sendMessage(User user, Message message) {
        try {
            user.getClientConnection().sendMessage(message);
        } catch (IOException e) {
            user.getClientConnection().close();
        }
    }

    public void broadcastMessage(Message message) {
        for (int i = 0; i < userCount; i++) {
            if (users[i] != null && users[i].getClientConnection() != null) {
                try {
                    users[i].getClientConnection().sendMessage(message);
                } catch (IOException e) {
                    users[i].getClientConnection().close();
                }
            }
        }
    }

    private void nextPlayerTurn() {
        final int playerId = (this.currentMovingPlayer + 1) % (this.userCount);
        LOGGER.info("Next player id is " + playerId);

        this.currentMovingPlayer = playerId;
        this.currentTurnUserId = users[playerId].getId();
        this.diceValue = 0;

        sendMessage(users[playerId], new TurnMessage());
    }

    /**
     * Triggers the dice for the current user which has the turn in the game
     */
    public void triggerDice() {
        this.diceValue = RANDOM.nextInt(DICE_MAX) + 1;
        this.diceThrownTimestamp = ServerEnvironment.getCurrentTimeStamp();
        boolean movesAvailable = gameMap.playerCanMoveAnyTokens(currentMovingPlayer, diceValue);

        TriggerDiceResult message = new TriggerDiceResult();
        message.setDiceValue(diceValue);
        message.setAnyTokensValid(movesAvailable);
        broadcastMessage(message);

        if (!movesAvailable) {
            nextPlayerTurn();
        }
    }

    /**
     * Moves the token for a player and makes the move if the move is possible.
     * @param tokenId Id of the token which the user wants to move.
     */
    public void moveToken(int tokenId) {
        // check if the user has rolled the dice yet
        if (diceValue <= 0) {
            return;
        }

        this.diceThrownTimestamp = Integer.MAX_VALUE;

        boolean canMoveToken = gameMap.playerCanMove(currentMovingPlayer, tokenId, diceValue);

        MoveTokenResult message = new MoveTokenResult();
        message.setValidMove(canMoveToken);
        sendMessage(users[currentMovingPlayer], message);

        if (canMoveToken) {
            gameMap.makeTurn(currentMovingPlayer, tokenId, diceValue);
            nextPlayerTurn();
        }
    }

    /**
     * Returns true if the dice has been thrown in this turn.
     * @return True if dice triggered in this turn, otherwise false.
     */
    public boolean diceTriggered() {
        return this.diceValue > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tokenUpdated(int factionId, int tokenId, int position) {
        TokenMovedMessage movedMessage = new TokenMovedMessage();
        movedMessage.setFactionMoving(factionId);
        movedMessage.setTokenId(tokenId);
        movedMessage.setNewPosition(position);
        broadcastMessage(movedMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameOver(int triggeringFaction) {
        // TODO: Not yet implemented
    }
}
