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
     * Check for blockades between two tiles.
     * @param factionId int users id
     * @param currentPosition int tokens current position
     * @param target int target to move
     * @return int the position of the block.
     */
    public int isBlocked(int factionId, int currentPosition, int target) {

        int firstFinishTileIndex = player[factionId].getStartOfFinishTileIndex();
        int numberOfTilesToCheck = target - currentPosition;
        boolean blocked = false;
        int index = 0;

        System.out.println("Tiles between #" + numberOfTilesToCheck);

        // No point looking for blockades on finish tiles OR when getting them out of the base:
        if ((currentPosition + 1) > firstFinishTileIndex  ||  currentPosition < 4) {
            return 0;
        }

        // Get first Blockade:
        while (!blocked  &&  ++index < numberOfTilesToCheck) {
            int mapPosition = currentPosition + index;
            blocked = tile[mapPosition].isBlocked(factionId);
            System.out.println("Block " + blocked + " " + (currentPosition + index));
        }

        if (index == numberOfTilesToCheck) {
            return 0;
        }

        return currentPosition + index;
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

        if (currentPosition < 4) {
            if (dice > 0) {
                target = 4;
            }
        } else {
            target += dice;
        }

        System.out.println("Moving token from (player) " + currentPosition + " -> " + target);
        // Moves the token right behind the blockade.

        /*
        int blockade = isBlocked(factionId, currentPosition, target);
        System.out.println("blockade on " + blockade);
        if (blockade > 0) {
            target = blockade -1;
        }
        */

        int last = player[factionId].getEndTileIndex();
        if (target > last) {
            int diff = target - last;
            target = last - diff;
        }

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
        System.out.println("------ START MAKE TURN ------ ");
        Token currentToken = player[factionId].getToken(tokenId);
        int currentMapPosition = player[factionId].getTokenMapPosition(tokenId);

        int target = getTargetTileIndex(factionId, currentToken.getPosition(), dice);
        int targetMapPosition = player[factionId].getTileIndex(target);
        System.out.println("Moving token from " + currentMapPosition + " -> " + targetMapPosition);

        if (currentToken.getPosition() != target) {

            Token move = tile[currentMapPosition].remove();
            move.setPosition(target);

            Token backToBase = tile[targetMapPosition].addToken(move);

            if (backToBase != null) {
                System.out.println("*** ON CAPTURE ***");

                int enemyFactionId = backToBase.getFaction();
                int enemyHomePosition = getEmptyBasePosition(enemyFactionId);

                int homeMapPosition = player[enemyFactionId].getTileIndex(enemyHomePosition);
                backToBase.setPosition(enemyHomePosition);

                tile[homeMapPosition].addToken(backToBase);
                System.out.println("Adding token to " + homeMapPosition + " (" + enemyHomePosition + ")");

                System.out.println("Captured tokenId " + backToBase.getTokenId());
                this.listener.playerKickedBackToBase(enemyFactionId, backToBase.getTokenId());
            }
        }
        this.listener.tokenUpdated(factionId, tokenId, target);
    }


    public int getEmptyBasePosition(int factionId) {
        for (int i=0; i<MAX_TOKENS; i++) {
            int basePosition = player[factionId].getTileIndex(i);
            if (tile[basePosition].isEmpty()) {
                return i;
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
