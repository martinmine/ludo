package no.hig.imt3281.ludo.client.gui.game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Thomas on 05.11.2014.
 *
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
     * For passing a tile checking for blockades.
     * @return boolean if there is a blockade or not.
     */
    public boolean isBlocked(Faction faction) {
        return (!tile.isEmpty()  &&  tile.get(0).getFaction() != faction  &&  tile.size() > 1);
    }

    /**
     * When removing blockades, the top token is removed first.
     * @return the last token added.
     */
    public Token remove() {
        System.out.println("trying to remove (size) = " + tile.size());
        return tile.remove(tile.size()-1);
    }

    /**
     * Get position of top Token on block if any.
     * @return int top Token position
     */
    public int getPosition() {
        if (tile.isEmpty()) {
            return -1;
        }
        return tile.get(tile.size()-1).getPosition();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * All tiles checks for token and draw if any.
     * @param g2d Graphics2D from painting the board.
     */
    public void draw(Graphics2D g2d) {

        /*
        Rectangle2D.Double r = new Rectangle2D.Double(x, y, d, d);
        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(r);
        */

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

}
