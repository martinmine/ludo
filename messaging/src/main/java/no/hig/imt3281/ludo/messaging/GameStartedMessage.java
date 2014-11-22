package no.hig.imt3281.ludo.messaging;

/**
 * Message sent to each client in a game which tells them that the game has started
 * and which faction the user has been assigned.
 */
public class GameStartedMessage extends Message {
    private int faction;


    public int getFaction() {
        return faction;
    }

    public void setFaction(int faction) {
        this.faction = faction;
    }
}
