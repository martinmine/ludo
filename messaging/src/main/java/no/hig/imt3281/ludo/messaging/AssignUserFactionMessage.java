package no.hig.imt3281.ludo.messaging;

/**
 * Created by Martin on 24.11.2014.
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