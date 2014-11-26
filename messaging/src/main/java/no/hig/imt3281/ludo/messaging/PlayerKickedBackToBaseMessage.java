package no.hig.imt3281.ludo.messaging;

/**
 * Message being sent when a user kicks another user back to the base.
 */
public class PlayerKickedBackToBaseMessage extends Message {
    private int faction;
    private int tokenId;

    public int getFaction() {
        return faction;
    }

    public void setFaction(int faction) {
        this.faction = faction;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }
}
