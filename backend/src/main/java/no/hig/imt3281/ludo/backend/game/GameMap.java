package no.hig.imt3281.ludo.backend.game;

/**
 * The game map contains the core game logic and manages player movement and the state of the game map.
 */
public class GameMap {

    private static final int MAX_PLAYERS = 4;
    private static final int MAX_TOKENS = 4;
    private static final int MAX_TILES = 92;
    private GameMapUpdateListener listener;
    private Tile tile[];
    private Player player[];

    /**
     * Prepares a new game map for use
     * @param listener The listener to receive notifications when changes are done to the map
     */
    public GameMap(GameMapUpdateListener listener) {
        this.listener = listener;
        player = new Player[MAX_PLAYERS];
        for (int i=0; i<MAX_PLAYERS; i++) {
            player[i] = new Player(i);
        }

        tile = new Tile[MAX_TILES];
        for (int i=0; i<MAX_TILES; i++) {
            tile[i] = new Tile();
        }

        for (int i=0; i<MAX_PLAYERS; i++) {
            for (int j=0; j<MAX_TOKENS; j++) {
                Token token = player[i].getToken(j);
                tile[player[i].getTokenMapPosition(j)].addToken(token);
            }
        }
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
     * Checks if there is any blockade between two tiles.
     * @param factionId int playerId RED, BLUE etc...
     * @param tokenId int players token 0-3
     * @param steps int the value on the dice
     * @return int The position were its a blockade. You can move just behind the blockade
     */
    public int isBlocked(final int factionId, final int tokenId, final int steps) {
        int firstFinishTileIndex = player[factionId].getStartOfFinishTileIndex();
        int currentTileIndex = player[factionId].getTokenMapPosition(tokenId); // player index
        System.out.println("isBlocked | currentTileIndex: " + currentTileIndex);
        int numTiles = steps + 1;

        // No point looking for blockades on finish tiles:
        if (currentTileIndex >= firstFinishTileIndex) {
            return 0;
        }

        boolean blocked = false;
        int i = 0;

        while (!blocked  &&  ++i < numTiles) {
            int index = player[factionId].getTileIndex(currentTileIndex + i);
            blocked = tile[index].isBlocked(factionId);
        }

        if (i == numTiles) {
            return 0;
        }

        return currentTileIndex + i;
    }

    /**
     * Calculate target position with current position (from players point of view) and dice value.
     * simply current position + dice.
     * @param factionId
     * @param currentPosition int current position from players point of view.
     * @param dice int the value on the dice.
     * @return int target for which position the token should move.
     */
    public int getTargetTileIndex(final int factionId, int currentPosition, final int dice) {
        int target = currentPosition;
        System.out.println("CURRENT POSITION: " + currentPosition);
        if (currentPosition < 4) {
            if (dice > 0) {
                target = 4;
            }
        } else {
            target += dice;
        }

        /*
        int blockade = isBlocked(factionId, currentPosition, target);
        if (blockade > 0) {
            target = blockade -1;
        }
        */

        /*
        int last = player[factionId].getEndTileIndex();
        if (target > last) {
            int diff = target - last;
            target = last - diff;
        }
        */

        return target;
    }

    /**
     * Makes the turn for the user and updates the game map and notifies
     * the listener for the changes that was made to the game map.
     * @param factionId id of the faction/color of the player [0-3]
     * @param tokenId id of the token [0-3]
     * @param dice how many steps the user wants to take from the dice
     */
    public void makeTurn(final int factionId, final int tokenId, final int dice) {
        System.out.println("tokenId " + tokenId);
        Token currentToken = player[factionId].getToken(tokenId);
        int currentMapPosition = player[factionId].getTokenMapPosition(tokenId);

        int target = getTargetTileIndex(factionId, currentToken.getPosition(), dice);
        int targetMapPosition = player[factionId].getTileIndex(target);
        System.out.println("Moving token from " + currentMapPosition + " -> " + targetMapPosition);

        if (currentToken.getPosition() != target) {

            System.out.println("!!!!Removing from (map) " + currentMapPosition);
            Token move = tile[currentMapPosition].remove();

            move.setPosition(target);
            System.out.println("new target for token is " + move.getPosition());

            Token backToBase = tile[targetMapPosition].addToken(move);

            System.out.println("");

            if (backToBase != null) {
                int home = getBaseTilePosition(factionId);
                tile[home].addToken(backToBase);
            }

        }
        this.listener.tokenUpdated(factionId, tokenId, target);

    }

    public int getBaseTilePosition(int factionId) {
        for (int i=0; i<4; i++) {

            int basePosition = player[factionId].getTileIndex(0);

            if (tile[basePosition].isEmpty()) {
                return basePosition;
            }
        }
        return -1;
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
