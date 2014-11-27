package no.hig.imt3281.ludo.client.gui.game;

import java.awt.*;

/**
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

    /**
     * Getting an Color object for drawing tokens.
     * @return Color object. drawing token.
     */
    public Color getColor() {
        switch(index) {
            case 0: return new Color(100,   0,   0);
            case 1: return new Color(  0,   0, 100);
            case 2: return new Color(100, 100,   0);
            case 3: return new Color(  0, 100,   0);
        }
        return null;
    }

    /**
     * Getting enum of a number. Necessary since
     * backend does not know about this enum, and needs to be
     * "converted"
     * @param index int playerId.
     * @return The Enum value.
     */
    public static Faction getFaction(int index) {
        switch(index) {
            case 0: return RED; 
            case 1: return BLUE;
            case 2: return YELLOW;
            case 3: return GREEN;
            default: return null;
        }
    }

}
