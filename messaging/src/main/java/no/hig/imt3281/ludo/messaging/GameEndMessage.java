package no.hig.imt3281.ludo.messaging;

/**
 * Message being sent to a client when the game has ended for whatever reason.
 * A game will end for the user when the user has been inactive beyond the timeout
 * period, or if the user did not win or if the user won the game.
 */
public class GameEndMessage extends Message {
    /**
     * The receiver won this game
     */
    public static final int WON = 0;
    /**
     * The receiver lost this game.
     */
    public static final int LOST = 1;
    /**
     * The receiver was kicked from the game.
     */
    public static final int KICKED = 2;

    private int gameResult;

    public int getGameResult() {
        return gameResult;
    }

    public void setGameResult(int gameResult) {
        this.gameResult = gameResult;
    }
}
