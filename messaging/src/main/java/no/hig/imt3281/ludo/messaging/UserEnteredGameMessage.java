package no.hig.imt3281.ludo.messaging;

import java.util.LinkedList;
import java.util.List;

/**
 * Message sent when a user has entered or joined a game.
 */
public class UserEnteredGameMessage extends Message {
    private int gameId;
    private List<Integer> players;
    private int playerCount;

    public UserEnteredGameMessage() {
        this.players = new LinkedList<>();
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public List<Integer> getPlayers() {
        return players;
    }

    public void setPlayers(List<Integer> players) {
        this.players = players;
    }

    public void addPlayerId(int userId) {
        this.players.add(userId);
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
