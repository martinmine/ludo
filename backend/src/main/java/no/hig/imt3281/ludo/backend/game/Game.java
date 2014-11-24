package no.hig.imt3281.ludo.backend.game;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.*;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Contains user management, communication and interaction with the game map
 */
public class Game implements GameMapUpdateListener {
    public static final int PLAYERS_MAX = 4;
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    private static final Random RANDOM = new Random();
    private static final int DICE_MAX = 6;
    private static final int TURN_TIMEOUT = 30;

    private int gameId;
    private User[] users;
    private int userCount;
    private boolean gameActive;
    /**
     * Index of the current turn player
     */
    private int currentMovingPlayer;
    /**
     * User id of the current turn player/faction
     */
    private int currentFactionTurn;
    /**
     * Value of the dice
     */
    private int diceValue;
    private int lastGameActionTimestamp;
    private GameMap gameMap;

    public Game(final int gameId) {
        this.gameId = gameId;
        this.users = new User[PLAYERS_MAX];
        this.currentMovingPlayer = -1;
        this.lastGameActionTimestamp = Integer.MAX_VALUE - TURN_TIMEOUT;
        this.gameMap = new GameMap(this);
        this.gameActive = true;
    }

    /**
     * Gets the user id of the user which makes the current turn in the game
     * @return user id
     */
    public int getCurrentFactionTurn() {
        return currentFactionTurn;
    }

    public int getGameId() {
        return gameId;
    }

    public void enter(User user) {
        final int factionId = userCount++;
        user.setCurrentGameId(this.gameId);
        user.setGamePlayerId(factionId);

        try {
            user.setTokensOnBoard();
        } catch (IOException e) {
            LOGGER.severe("Joining game failed");
        }

        users[factionId] = user;
    }

    public synchronized void leave(User user) {
        if (user.getCurrentGameId() != this.gameId)
            return;

        int faction = user.getGameFactionId();

        if (faction < 0) {
            return;
        }

        this.users[faction] = null;
        this.gameMap.clearMapForPlayer(faction);

        UserLeftGameMessage leaveMessage = new UserLeftGameMessage();
        leaveMessage.setFaction(faction);
        broadcastMessage(leaveMessage);

        // If there is only one remaining user, the remaining user wins the game
        if (activePlayerCount() == 1) {
            GameEndMessage message = new GameEndMessage();
            message.setGameResult(GameEndMessage.WON);
            broadcastMessage(message);

            // The game is over
            destroy();
        } else if (faction == currentFactionTurn) {
            nextPlayerTurn();
        }
    }

    private void destroy() {
        this.gameActive = false;
        ServerEnvironment.getGameManager().reportGameDestroyed(this.gameId);
    }

    private int activePlayerCount() {
        int playerCount = 0;
        for (int i = 0; i < userCount; i++) {
            if (users[i] != null && users[i].getCurrentGameId() == this.gameId) {
                playerCount++;
            }
        }

        return playerCount;
    }

    public void cycle() {
        if (ServerEnvironment.getCurrentTimeStamp() > lastGameActionTimestamp + TURN_TIMEOUT) {
            User kickingUser = users[currentMovingPlayer];
            LOGGER.info("Scrublord " + kickingUser.getUsername() + " timed out");
            leave(kickingUser);
            GameEndMessage leaveMessage = new GameEndMessage();
            leaveMessage.setGameResult(GameEndMessage.KICKED);
            sendMessage(kickingUser, leaveMessage);
        }
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
            sendMessage(users[i], startMessage);
        }

        nextPlayerTurn();
    }

    private void sendMessage(User user, Message message) {
        if (user.getCurrentGameId() == this.gameId && user.getClientConnection() != null) {
            try {
                user.getClientConnection().sendMessage(message);
            } catch (IOException e) {
                user.getClientConnection().close();
            }
        } else {
            LOGGER.warning("COULD NOT SEND STUFF TO USER");
            //leave(user);
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
        // Finds a new player id that is active and in the game
        do {
            this.currentMovingPlayer = (this.currentMovingPlayer + 1) % (this.userCount);
        } while (users[currentMovingPlayer] == null || users[currentMovingPlayer].getCurrentGameId() != this.gameId);
        LOGGER.info("New player id is " + currentMovingPlayer);

        this.currentFactionTurn = users[currentMovingPlayer].getId();
        this.diceValue = 0;
        this.lastGameActionTimestamp = ServerEnvironment.getCurrentTimeStamp();

        sendMessage(users[currentMovingPlayer], new TurnMessage());
    }

    /**
     * Triggers the dice for the current user which has the turn in the game
     */
    public void triggerDice() {
        this.diceValue = RANDOM.nextInt(DICE_MAX) + 1;
        this.lastGameActionTimestamp = ServerEnvironment.getCurrentTimeStamp();
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

        this.lastGameActionTimestamp = ServerEnvironment.getCurrentTimeStamp();

        boolean canMoveToken = gameMap.playerCanMove(currentMovingPlayer, tokenId, diceValue);

        MoveTokenResult message = new MoveTokenResult();
        message.setValidMove(canMoveToken);
        sendMessage(users[currentMovingPlayer], message);

        if (canMoveToken) {
            gameMap.makeTurn(currentMovingPlayer, tokenId, diceValue);
            nextPlayerTurn();
        } else {
            // User has done something, reset the timer so he doesn't get kicked
            this.lastGameActionTimestamp = ServerEnvironment.getCurrentTimeStamp();
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
        GameEndMessage message = new GameEndMessage();
        for (int i = 0; i < userCount; i++) {
            if (users[i] != null && users[i].getClientConnection() != null) {
                if (i == triggeringFaction) {
                    message.setGameResult(GameEndMessage.WON);
                } else {
                    message.setGameResult(GameEndMessage.LOST);
                }

                sendMessage(users[i], message);
            }
        }

        destroy();
    }
}
