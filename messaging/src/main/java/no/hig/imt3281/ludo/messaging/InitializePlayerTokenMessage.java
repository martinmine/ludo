package no.hig.imt3281.ludo.messaging;

/**
 * Messages sent when a user joins the game.
 */
public class InitializePlayerTokenMessage extends Message {

    private int playerId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int id) {
        playerId = id;
    }

}
