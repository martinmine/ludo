package no.hig.imt3281.ludo.client.gui.game;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Thomas on 05.11.2014.
 * A Token represent a players piece.
 * Handling drawing of a token.
 */
public class Token {

    // For drawing a Token (same height & width)
    private static final int SQR_SIZE = 30;

    private Ellipse2D.Double rawToken;
    private Faction faction;

    // Knowing its own position from the owners point og view:
    private int position;
    private int tokenId;

    /**
     * Setting up a token on create.
     * @param faction owner of the token.
     * @param position position on the map.
     * @param tokenId players id on the token.
     */
    Token(Faction faction, int position, int tokenId) {
        this.rawToken = new Ellipse2D.Double(0, 0, SQR_SIZE, SQR_SIZE);
        this.faction = faction;
        this.position = position;
        this.tokenId = tokenId;
    }

    /**
     * Drawing a Token on top of board.
     * @param g2d Graphics2D past from paintComponent
     * @param x int X-coordinate
     * @param y int Y-coordinate
     */
    public void draw(Graphics2D g2d, int x, int y) {
        this.rawToken.setFrame(x + 2, y + 2, SQR_SIZE, SQR_SIZE);
        g2d.setColor(faction.getColor());
        g2d.fill(rawToken);
    }

    /**
     * Checking if the token is equal.
     * @param t Token obj, comparing token.
     * @return boolean, true if the same.
     */
    public boolean isEqual(Token t) {
        return t.faction == faction;
    }

    /**
     * Getting the owner of the token.
     * @return faction, player id.
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Changing the tokens position on the map.
     * @param position int maps position.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * getting the tokens position on the map.
     * @return int, the tokens map position.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * The token id from the player.
     * @return int, tokenid.
     */
    public int getTokenId() {
        return tokenId;
    }
}
