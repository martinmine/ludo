package no.hig.imt3281.ludo.backend.game;

/**
 * Created by Thomas on 24.11.2014.
 */
public class Token {

    private int position;
    private int faction;
    private int tokenId;

    public Token(int faction, int position, int tokenId) {
        this.faction = faction;
        this.position = position;
        this.tokenId = tokenId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getFaction() {
        return faction;
    }

    public int getTokenId() {
        return tokenId;
    }
}
