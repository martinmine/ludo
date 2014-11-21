package no.hig.imt3281.ludo.messaging;

/**
 * Created by Thomas on 21.11.2014.
 * unhandled...
 */
public class MoveTokenRequest extends Message {

    private int tokenId;

    public MoveTokenRequest() {

    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }
}
