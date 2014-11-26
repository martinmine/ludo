package no.hig.imt3281.ludo.backend.game;

/**
 * Listener for receiving events that happens with the game map.
 */
public interface GameMapUpdateListener {
    /**
     * Callback listener called when a token has been moved on the board and the listener
     * needs to know that the token was moved. Should notify clients of the move.
     * @param factionId The faction that moves the token
     * @param tokenId Id of the token that was moved
     * @param position The new position of the token
     */
    public void tokenUpdated(final int factionId, final int tokenId, final int position);

    /**
     * Callback when the game ends.
     * @param triggeringFaction The id of the faction that wins. Can be -1 if no faction wins.
     */
    public void gameOver(final int triggeringFaction);

    /**
     * Callback once a player is kicked back to the home base.
     * @param factionId Id of the faction being kicked.
     * @param tokenId Id of the player's token being kicked.
     */
    public void playerKickedBackToBase(int factionId, int tokenId);
}
