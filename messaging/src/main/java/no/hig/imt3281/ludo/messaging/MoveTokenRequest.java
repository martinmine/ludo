package no.hig.imt3281.ludo.messaging;

/**
 * Message sent when the user wants to move their token.
 */
public class MoveTokenRequest extends Message {
    private int tokenId;

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }
}
