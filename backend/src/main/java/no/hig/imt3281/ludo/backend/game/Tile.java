package no.hig.imt3281.ludo.backend.game;

/**
 * Created by Thomas on 24.11.2014.
 *
 */
public class Tile {

    public static final int MAX_TOKENS = 4;
    private Token token[];
    private int numToken;

    public Tile() {
        token = new Token[MAX_TOKENS];
        numToken = 0;
    }

    public Token addToken(Token t) {
        if (numToken == 0) {
            token[numToken++] = t;
        } else {
            if (token[0].getFaction() == t.getFaction()) {
                token[numToken++] = t;
            } else {
                Token temp = token[0];
                token[numToken] = t;
                return temp;
            }
        }
        return null;
    }

    public boolean isBlocked(int faction) {
        return (numToken != 0  && token[0].getFaction() != faction  &&  numToken > 1);
    }

    public Token remove() {
        return token[numToken--];
    }

    public boolean isEmpty() {
        return (numToken == 0);
    }

    public int getFaction() {
        return (numToken == 0)? -1: token[0].getFaction();
    }

}
