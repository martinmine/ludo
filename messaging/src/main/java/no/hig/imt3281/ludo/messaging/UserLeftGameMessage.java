package no.hig.imt3281.ludo.messaging;

/**
 * Message sent when a user has left the game for whatever reason.
 */
public class UserLeftGameMessage extends Message {
    private int faction;

    public int getFaction() {
        return faction;
    }

    public void setFaction(int faction) {
        this.faction = faction;
    }
}
