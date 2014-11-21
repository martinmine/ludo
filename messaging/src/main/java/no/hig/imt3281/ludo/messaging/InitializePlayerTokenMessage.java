package no.hig.imt3281.ludo.messaging;

/**
 * Created by Thomas on 21.11.2014.
 */
public class InitializePlayerTokenMessage extends Message {

    private int playerId;

    public int getPlayerId() {
        return playerId;
    }

}
