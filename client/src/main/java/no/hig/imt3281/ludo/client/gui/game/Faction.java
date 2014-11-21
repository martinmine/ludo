package no.hig.imt3281.ludo.client.gui.game;

import java.awt.*;

/**
 * Created by Thomas on 05.11.2014.
 * Enum to distinguish between players by color.
 */
public enum Faction {
    RED(0),
    BLUE(1),
    YELLOW(2),
    GREEN(3);

    private final int index;

    Faction(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Color getColor() {
        switch(index) {
            case 0: return new Color(100,   0,   0);
            case 1: return new Color(  0,   0, 100);
            case 2: return new Color(100, 100,   0);
            case 3: return new Color(  0, 100,   0);
        }
        return null;
    }

}
