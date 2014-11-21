package no.hig.imt3281.ludo.client.gui.game;

/**
 * Created by Thomas on 21.11.2014.
 *
 */
public class Player {

    public final static int MAX_PLAYERS = 4;
    public final static int MAX_TOKENS = 4;

    // faction may be useless here...
    private Faction faction;
    private Token token[];

    public Player(Faction faction) {
        this.faction = faction;
        token = new Token[MAX_TOKENS];
        initTokens();
    }

    public Faction getFaction() {
        return faction;
    }

    private void initTokens() {
        for (int i=0; i<MAX_PLAYERS; i++) {
            token[i] = new Token(faction, i);
        }
    }

    public Token getToken(int tokenId) {
        return token[tokenId];
    }

}
