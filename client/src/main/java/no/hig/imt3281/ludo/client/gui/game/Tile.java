package no.hig.imt3281.ludo.client.gui.game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Controlling all access to a tile.
 * adding and knowing its position on the board, both for
 * drawing and listening to mouse events.
 */
public class Tile {

    private ArrayList<Token> tile;
    private int x;
    private int y;
    private int d;

    Tile(int x, int y, int d) {
        this.tile = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.d = d;
    }

    /**
     * Adding a token to a tile, there is outcomes.
     * When its empty, building blockade, or kicking an opponent back to base.
     *
     * @param t Token to be added.
     * @return Token kicking out enemy Token back to base.
     */
    public Token addToken(Token t) {

        // Empty Tile:
        if (tile.isEmpty()) {
            tile.add(t);
        } else {

            // Already same Token - build blockade:
            // else kick opponents token.
            if (tile.get(0).isEqual(t)) {
                tile.add(t);
            } else {
                Token temp = tile.remove(0);
                tile.add(t);
                return temp;
            }
        }
        return null;
    }

    /**
     * When removing blockades, the top token is removed first.
     * @return the last token added.
     */
    public Token remove() {
        return tile.remove(tile.size()-1);
    }

    /**
     * All tiles checks for token and draw if any.
     * @param g2d Graphics2D from painting the board.
     */
    public void draw(Graphics2D g2d) {

        if (!tile.isEmpty()) {
            tile.get(0).draw(g2d, this.x, this.y);
            if (tile.size() > 1) {
                g2d.setColor(Color.WHITE);
                g2d.drawString(String.valueOf(tile.size()), this.x + 20, this.y + 20);
            }
        }
    }

    /**
     * For check if a Tile was clicked.
     * @param x int X-coordinate
     * @param y int Y-coordinate
     * @return boolean if it was clicked.
     */
    public boolean clicked(int x, int y) {
        int xx = this.x + d;
        int yy = this.y + d;
        return (x >= this.x  &&  y >= this.y  &&  x <= xx  &&  y <= yy);
    }

    /**
     * Check if the Tile is empty before accessing it.
     * @return boolean isEmpty.
     */
    public boolean isEmpty() {
        return tile.isEmpty();
    }

    /**
     * Getting the owner (faction) of the tile.
     * For checking on rival blockades.
     * @return Faction owner of the Tile.
     */
    public Faction getFaction() {
        return (tile.isEmpty())? null: tile.get(0).getFaction();
    }

    /**
     * Players tokenId for the player
     * @return int uniq tokenId
     */
    public int getTokenID() {
        if (tile.isEmpty()) {
            return -1;
        }
        return tile.get(tile.size() -1 ).getTokenId();
    }

    /**
     * Removes all the tokens from the tile
     */
    public void clear() {
        this.tile.clear();
    }
}
