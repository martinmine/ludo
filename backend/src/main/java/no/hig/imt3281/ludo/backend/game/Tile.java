package no.hig.imt3281.ludo.backend.game;

import java.util.Arrays;

/**
 * Controls a square on the map.
 * Adding and removing tokens, working with building
 * blockades for players.
 */
public class Tile {

    public static final int MAX_TOKENS = 4;
    private Token token[];
    private int numToken;

    /**
     * Setting up a tile with zero tokens.
     */
    public Tile() {
        token = new Token[MAX_TOKENS];
        numToken = 0;
    }

    /**
     * Adding a token to a tile has 3 outcomes.
     *
     * First:   It's empty, simply adding the new token.
     * Second:  Not empty, which means building a blockade.
     * Third:       or throwing another token back to base.
     *
     * One Token will never land on a blockade, it will always
     * be placed one tile behind it.
     *
     * @param t Obj The Token to be added.
     * @return The kicked token, else null.
     */
    public Token addToken(Token t) {
        if (numToken == 0) {
            token[numToken++] = t;
        } else {
            if (token[0].getFaction() == t.getFaction()) {
                token[numToken++] = t;
            } else {
                Token temp = token[0];
                token[0] = t;
                return temp;
            }
        }
        return null;
    }

    /**
     * Checking it the tile has an blockade for a certain player.
     * @param faction Checking if the blockade belongs to another player.
     * @return Boolean True of there is an blockade.
     */
    public boolean isBlocked(int faction) {
        return (numToken > 1  && token[0].getFaction() != faction);
    }

    /**
     * Removing a token from the tile.
     * In blockades this is the top token.
     * @return Obj The token object.
     */
    public Token remove() {
        numToken--;
        return token[numToken];
    }

    /**
     * For checking if the tile is empty.
     * On finding a empty tile for kicking other plays token
     * back to base.
     * @return Boolean True if the tile has no tokens.
     */
    public boolean isEmpty() {
        return (numToken == 0);
    }

    /**
     * Knowing how tall the blockade are is important
     * to find how wins the game, having all their tokens
     * as a blockade on their finish tile.
     * @return int The number of tokens.
     */
    public int getBlockSize() {
        return numToken;
    }

    /**
     * Clearing the tile for all tokens.
     * For cleaning up after player leave.
     */
    public void clear() {
        Arrays.stream(token).map(tt -> tt = null);
        numToken = 0;
    }

}
