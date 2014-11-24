package no.hig.imt3281.ludo.backend.game;

/**
 * Created by marti_000 on 22.11.2014.
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
}
