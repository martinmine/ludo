package no.hig.imt3281.ludo.client.gui.game;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Thomas on 05.11.2014.
 *
 */
public class Token {

    private static final int SQR_SIZE = 30; // width and height;

    private Ellipse2D.Double token;
    private Faction faction;
    private int position;

    Token(Faction faction, int position) {
        this.token = new Ellipse2D.Double(0, 0, SQR_SIZE, SQR_SIZE);
        this.faction = faction;
        this.position = position;
    }

    public void draw(Graphics2D g2d, int x, int y) {
        this.token.setFrame(x + 2, y + 2, SQR_SIZE, SQR_SIZE);
        g2d.setColor(faction.getColor());
        g2d.fill(token);
    }

    public boolean equals(Token t) {
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
