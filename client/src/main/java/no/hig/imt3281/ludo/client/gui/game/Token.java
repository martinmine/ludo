package no.hig.imt3281.ludo.client.gui.game;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Thomas on 05.11.2014.
 * A Token represent a players piece.
 */
public class Token {

    // For drawing a Token (same height & width)
    private static final int SQR_SIZE = 30;

    private Ellipse2D.Double rawToken;
    private Faction faction;

    // Knowing its own position from the owners point og view:
    private int position;

    Token(Faction faction, int position) {
        this.rawToken = new Ellipse2D.Double(0, 0, SQR_SIZE, SQR_SIZE);
        this.faction = faction;
        this.position = position;
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

    public boolean isEqual(Token t) {
        return t.faction == faction;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }
}
