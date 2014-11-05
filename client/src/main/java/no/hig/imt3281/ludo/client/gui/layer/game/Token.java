package no.hig.imt3281.ludo.client.gui.layer.game;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Thomas on 05.11.2014.
 *
 */
public class Token {

    private Ellipse2D.Double token;
    private Faction faction;

    Token(Faction faction, int x, int y, int d) {
        this.token = new Ellipse2D.Double(x, y, d, d);
        this.faction = faction;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(faction.getColor());
        g2d.fill(token);
    }

    public boolean equals(Token t) {
        return t.faction == faction;
    }

}
