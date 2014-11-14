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
     * Adding a token to a tile, there is 3 checks.
     * When its empty, building blockade, or kicking an opponent back to base.
     *
     * @param t Token to be added.
     */
    public Token addToken(Token t) {
        if (tile.isEmpty()) {               // Free tile:
            tile.add(t);
        } else {
            if (tile.get(0).equals(t)) {    // Building blockade;
                tile.add(t);
            } else {                        // Kicking an opponent:
                Token temp = tile.remove(0);
                tile.add(t);
                return temp;
            }
        }
        return null;
    }

    /**
     * For passing this tile, check on blockades.
     * TODO: can merge with pass ?
     * @return int number of tokens in this tile.
     */
    public boolean isBlocked(Faction faction) {
        return (!tile.isEmpty()  &&  tile.get(0).getFaction() != faction  &&  tile.size() > 1);
    }

    /**
     * When removing blockades, the top token is removed first.
     * @return the last token added.
     */
    public Token remove() {
        return tile.remove(tile.size()-1);
    }

    public int getPosition() {
        if (tile.isEmpty()) {
            return -1;
        }
        return tile.get(0).getPosition();
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

    public boolean clicked(int x, int y) {
        int xx = this.x + d;
        int yy = this.y + d;
        return (x >= this.x  &&  y >= this.y  &&  x <= xx  &&  y <= yy);
    }

    public boolean isEmpty() {
        return tile.isEmpty();
    }

    public Faction getFaction() {
        if (tile.isEmpty()) return null;
        return tile.get(0).getFaction();
    }



}
