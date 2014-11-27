package no.hig.imt3281.ludo.client.gui.game;

import java.util.ArrayList;

/**
 * Controlling all player logic on the client.
 */
public class Player {

    public final static int MAX_PLAYERS = 4;
    public final static int MAX_TOKENS = 4;

    private Faction faction;
    private Token[] token;
    private ArrayList<Integer> tiles;

    public Player(Faction faction) {
        this.faction = faction;
        token = new Token[MAX_TOKENS];
        initTokens();
        tiles = new ArrayList<>();
        initPlayerTiles();
    }

    /**
     * Initializing tokens.
     */
    private void initTokens() {
        for (int i=0; i<MAX_PLAYERS; i++) {
            token[i] = new Token(faction, i, i);
        }
    }

    /**
     * Initializing player tiles.
     */
    private void initPlayerTiles() {

        switch(faction) {
            case RED:
                for (int i=76; i<80; i++) {
                    tiles.add(i);
                }

                for (int j=0; j<52; j++) {
                    tiles.add(j);
                }

                tiles.add(0);

                for (int k=52; k<58; k++) {
                    tiles.add(k);
                }
                break;
            case BLUE:
                for (int i=80; i<84; i++) {
                    tiles.add(i);
                }

                for (int j=0; j<39; j++) {
                    tiles.add(j+13);
                }

                for (int k=0; k<13; k++) {
                    tiles.add(k);
                }

                tiles.add(13);

                for (int l=58; l<64; l++) {
                    tiles.add(l);
                }
                break;
            case YELLOW:
                for (int i=84; i<88; i++) {
                    tiles.add(i);
                }

                for (int j=0; j<26; j++) {
                    tiles.add(j+26);
                }

                for (int k=0; k<26; k++) {
                    tiles.add(k);
                }

                tiles.add(26);

                for (int l=64; l<70; l++) {
                    tiles.add(l);
                }

                break;
            default:
            case GREEN:
                for (int i=88; i<92; i++) {
                    tiles.add(i);
                }

                for (int j=0; j<13; j++) {
                    tiles.add(j+39);
                }

                for (int k=0; k<39; k++) {
                    tiles.add(k);
                }

                tiles.add(39);

                for (int l=70; l<76; l++) {
                    tiles.add(l);
                }
                break;
        }
    }

    /**
     * Get a plyer token from an Token id.
     * @param tokenId int The id for which token to get.
     * @return Token obj.
     */
    public Token getToken(int tokenId) {
        return token[tokenId];
    }

    /**
     * Setting player tokens from tokens onn board.
     * @param tile List of actual mapping of the board.
     */
    public void setTokens(ArrayList<Tile> tile) {
        for (int i=0; i<MAX_TOKENS; i++) {
            tile.get(tiles.get(i)).addToken(token[i]);
        }
    }

    /**
     * Getting players actual tile index (map) by the players
     * own mapping of the board.
     * @param index Int the number of the tile.
     * @return int map index.
     */
    public int getTileIndex(int index) {
        return tiles.get(index);
    }

    /**
     * getting a tokens map position from a tokenId.
     * @param tokenId int tokenId.
     * @return int map index.
     */
    public int getTokenPosition(int tokenId) {
        return tiles.get(token[tokenId].getPosition());
    }

}
