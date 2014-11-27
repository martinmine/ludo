package no.hig.imt3281.ludo.messaging;

/**
 * Message for assigning a user a faction.
 */
public class AssignUserFactionMessage extends Message {
    private int faction;

    public int getFaction() {
        return faction;
    }

    public void setFaction(int faction) {
        this.faction = faction;
    }
}