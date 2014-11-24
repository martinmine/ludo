package no.hig.imt3281.ludo.backend.game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The game map contains the core game logic and manages player movement and the state of the game map.
 */
public class GameMap {

    private static final int MAX_TILES = 92;
    private GameMapUpdateListener listener;
    private int tiles[];

    /**
     * Prepares a new game map for use
     * @param listener The listener to receive notifications when changes are done to the map
     */
    public GameMap(GameMapUpdateListener listener) {
        this.listener = listener;

        tiles = new int[MAX_TILES];

        Arrays.stream(tiles).forEach(value -> value = 0);

        tiles[76] = 10;
        tiles[77] = 11;
        tiles[78] = 12;
        tiles[79] = 13;

        tiles[80] =

    }

    /**
     * Checks if a user can make a move
     * @param factionId id of the faction/color of the player [0-3]
     * @param tokenId id of the token [0-3]
     * @param steps how many steps the user wants to take from the dice
     * @return True if the player can make the turn, otherwise false
     */
    public boolean playerCanMove(final int factionId, final int tokenId, final int steps) {
        // TODO: not yet implemented
        return true;
    }

    /**
     * Makes the turn for the user and updates the game map and notifies
     * the listener for the changes that was made to the game map.
     * @param factionId id of the faction/color of the player [0-3]
     * @param tokenId id of the token [0-3]
     * @param dice how many steps the user wants to take from the dice
     */
    public void makeTurn(final int factionId, final int tokenId, final int dice) {
        // TODO: Update game map, update states for all affected tokens in the map

        this.listener.tokenUpdated(factionId, tokenId, 1 + dice);
    }

    /**
     * Checks if the user has any tokens which the user can move with this dice value (or amount of steps)
     * @param factionId id of the faction/color of the player [0-3]
     * @param steps how many steps the user wants to take from the dice
     * @return True if the player can move tokens, otherwise false
     */
    public boolean playerCanMoveAnyTokens(final int factionId, final int steps) {
        for (int i = 0; i < 3; i++) {
            if (playerCanMove(factionId, i, steps)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes all the tokens for a player from the map.
     * Example usage is when a scrublord loses towards a Ludo MLG
     * quickscoper and ragequits. The scrublord's tokens are
     * then removed from the game map.
     * @param factionId id of the faction which shall be removed from the game map
     */
    public void clearMapForPlayer(final int factionId) {
        // TODO: Not yet implemented, note: no need to notify listener, as this is notified to the client through an UserLeaveMessage
    }

    /**
     * Adds a faction (or a player) to the game map)
     * @param factionId Id of the faction that was added
     */
    public void addTokens(final int factionId) {

    }
}
