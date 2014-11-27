package no.hig.imt3281.ludo.backend.game;

import java.util.Arrays;

/**
 * The game map contains the core game logic and manages player movement and the state of the game map.
 */
public class GameMap {

    private static final int MAX_PLAYERS = 4;
    private static final int MAX_TOKENS = 4;
    private static final int MAX_TILES = 92;
    private GameMapUpdateListener listener;
    private Tile[] tile;
    private Player[] player;

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
        int mapPosition;

        // No point looking for blockades on finish tiles OR when getting them out of the base:
        if ((currentPosition + 1) > firstFinishTileIndex  ||  currentPosition < 4) {
            return 0;
        }

        // Get first Blockade:
        while (!blocked  &&  ++index <= numberOfTilesToCheck) {
            mapPosition = player[factionId].getTileIndex(currentPosition + index);
            blocked = tile[mapPosition].isBlocked(factionId);
        }

        if (index == (numberOfTilesToCheck+1)) {
            return 0;
        }

        return currentPosition + index;
    }

    /**
     * Calculate target position with current position (from players point of view) and dice value.
     * simply current position + dice.
     * @param factionId int players id.
     * @param currentPosition int current position from players point of view.
     * @param dice int the value on the dice.
     * @return int target for which position the token should move. (map)
     */
    public int getTargetTileIndex(final int factionId, int currentPosition, final int dice) {
        int target = currentPosition;

        if (currentPosition < 4) {
            if (dice >= 5) {
                target = 4;
            }
        } else {
            target += dice;
        }

        // Moves the token right behind the blockade.
        int blockade = isBlocked(factionId, currentPosition, target);
        if (blockade > 0) {
            target = blockade -1;
        }

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
        Token currentToken = player[factionId].getToken(tokenId);
        int currentMapPosition = player[factionId].getTokenMapPosition(tokenId);

        int target = getTargetTileIndex(factionId, currentToken.getPosition(), dice);
        int targetMapPosition = player[factionId].getTileIndex(target);

        if (currentToken.getPosition() != target) {

            Token move = tile[currentMapPosition].remove();
            move.setPosition(target);

            Token backToBase = tile[targetMapPosition].addToken(move);

            if (backToBase != null) {
                int enemyFactionId = backToBase.getFaction();
                int enemyHomePosition = getEmptyBasePosition(enemyFactionId);

                int homeMapPosition = player[enemyFactionId].getTileIndex(enemyHomePosition);
                backToBase.setPosition(enemyHomePosition);

                tile[homeMapPosition].addToken(backToBase);
                this.listener.playerKickedBackToBase(enemyFactionId, backToBase.getTokenId());
            }
        }
        this.listener.tokenUpdated(factionId, tokenId, target);
        checkForWinner();
    }

    /**
     * Checking if a player has all their tokens at their finish position.
     * The first player reaching the finish with all the tokens wins.
     */
    public void checkForWinner() {
        int finishPosition[] = {57, 63, 69, 75};

        int winner =
            Arrays.stream(finishPosition)
            .filter(position -> tile[position].getBlockSize() == MAX_TOKENS)
                    .findFirst()
            .orElse(-1);

        if (winner != -1) {
            this.listener.gameOver(0);
        }
    }

    /**
     * When a token is thrown back to base, one need to find a empty base tile.
     * @param factionId int player id.
     * @return int The players empty base tile index. (from player perspective: 0-3)
     */
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
     * Removes all the tokens for a player from the map.
     * Example usage is when a scrublord loses towards a Ludo MLG
     * quickscoper and ragequits. The scrublord's tokens are
     * then removed from the game map.
     * @param factionId id of the faction which shall be removed from the game map
     */
    public void clearMapForPlayer(final int factionId) {
        player[factionId].leave(tile);
    }
}
