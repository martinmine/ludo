package no.hig.imt3281.ludo.messaging;

/**
 * Message sent to all clients in a game indicating that one token has been moved.
 */
public class TokenMovedMessage extends Message {
    private int factionMoving;
    private int tokenId;
    private int newPosition;

    public int getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public int getFactionMoving() {
        return factionMoving;
    }

    public void setFactionMoving(int factionMoving) {
        this.factionMoving = factionMoving;
    }
}
