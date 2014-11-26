package no.hig.imt3281.ludo.backend.game;

import java.util.ArrayList;

/**
 * Created by Thomas on 24.11.2014.
 *
 */
public class Player {

    public final static int MAX_TOKENS = 4;

    private int faction;
    private ArrayList<Integer> tiles;
    private Token token[];

    public Player(int faction) {
        this.faction = faction;
        tiles = new ArrayList<>();
        initPlayerTiles();
        token = new Token[MAX_TOKENS];

        for (int i=0; i<MAX_TOKENS; i++) {
            token[i] = new Token(faction, i, i);
        }
    }

    private void initPlayerTiles() {

        switch(faction) {
            case 0:
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
            case 1:
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
            case 2:
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
            case 3:
                for (int i = 88; i < 92; i++) {
                    tiles.add(i);
                }
                for (int j = 0; j < 13; j++) {
                    tiles.add(j + 39);
                }
                for (int k = 0; k < 39; k++) {
                    tiles.add(k);
                }
                tiles.add(39);
                for (int l = 70; l < 76; l++) {
                    tiles.add(l);
                }

        }
    }

    public int getStartOfFinishTileIndex() {
        int firstFinishTileIndex = tiles.size() - 6;
        return tiles.get(firstFinishTileIndex);
    }

    public int getTokenMapPosition(int tokenId) {
        return tiles.get(token[tokenId].getPosition());
    }

    public Token getToken(int index) {
        return token[index];
    }

    public int getTileIndex(int index) {
        return tiles.get(index);
    }

    public int getEndTileIndex() {
        return tiles.size() - 1;
    }

}
