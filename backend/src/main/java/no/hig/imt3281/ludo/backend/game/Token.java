package no.hig.imt3281.ludo.backend.game;

/**
 * Controls all operations on a token
 * Accessing owner from a token directly was
 * necessary to check a token on the board for blockades.
 * You can go past your own blockades.
 */
public class Token {

    private int position;
    private int faction;
    private int tokenId;

    /**
     * Setting up a token on create.
     * @param faction owner of the token.
     * @param position the current position on the board.
     * @param tokenId int Token id for the player.
     */
    public Token(int faction, int position, int tokenId) {
        this.faction = faction;
        this.position = position;
        this.tokenId = tokenId;
    }

    /**
     * The position of the token on the map.
     * @return maps position on the board.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Changing the current position of the token.
     * @param position int the new position on the map
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Getting the owner of the token.
     * @return int the player id.
     */
    public int getFaction() {
        return faction;
    }

    /**
     * Getting the players tokenId.
     * @return int tokenId.
     */
    public int getTokenId() {
        return tokenId;
    }
}
