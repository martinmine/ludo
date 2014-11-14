package no.hig.imt3281.ludo.client.gui.game;

import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.client.gui.SideTopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Joakim on 27.10.2014.
 *
 */
public class GamePanel extends JComponent implements MouseListener {

    //private static final Logger LOGGER = Logger.getLogger(GamePanel.class.getName());

    private Dimension boardSize;
    private final static int TILE_SIZE = 35;
    private Image board;
    private ArrayList<Tile> tiles;
    private ArrayList<ArrayList<Integer>> tile;

    public GamePanel() {
        addMouseListener(this);
        tiles = new ArrayList<>();

        // SHARED TILES:
        tiles.add(new Tile(320,  57, TILE_SIZE));
        tiles.add(new Tile(320,  95, TILE_SIZE));
        tiles.add(new Tile(320, 132, TILE_SIZE));
        tiles.add(new Tile(320, 170, TILE_SIZE));
        tiles.add(new Tile(320, 207, TILE_SIZE));

        tiles.add(new Tile(358, 245, TILE_SIZE));
        tiles.add(new Tile(395, 245, TILE_SIZE));
        tiles.add(new Tile(433, 245, TILE_SIZE));
        tiles.add(new Tile(471, 245, TILE_SIZE));
        tiles.add(new Tile(508, 245, TILE_SIZE));

        tiles.add(new Tile(546, 245, TILE_SIZE));
        tiles.add(new Tile(546, 283, TILE_SIZE));
        tiles.add(new Tile(546, 320, TILE_SIZE));

        tiles.add(new Tile(508, 320, TILE_SIZE));
        tiles.add(new Tile(471, 320, TILE_SIZE));
        tiles.add(new Tile(433, 320, TILE_SIZE));
        tiles.add(new Tile(395, 320, TILE_SIZE));
        tiles.add(new Tile(358, 320, TILE_SIZE));

        tiles.add(new Tile(320, 358, TILE_SIZE));
        tiles.add(new Tile(320, 395, TILE_SIZE));
        tiles.add(new Tile(320, 433, TILE_SIZE));
        tiles.add(new Tile(320, 471, TILE_SIZE));
        tiles.add(new Tile(320, 508, TILE_SIZE));

        tiles.add(new Tile(320, 545, TILE_SIZE));
        tiles.add(new Tile(283, 545, TILE_SIZE));
        tiles.add(new Tile(245, 545, TILE_SIZE));

        tiles.add(new Tile(245, 508, TILE_SIZE));
        tiles.add(new Tile(245, 471, TILE_SIZE));
        tiles.add(new Tile(245, 433, TILE_SIZE));
        tiles.add(new Tile(245, 395, TILE_SIZE));
        tiles.add(new Tile(245, 358, TILE_SIZE));

        tiles.add(new Tile(207, 320, TILE_SIZE));
        tiles.add(new Tile(170, 320, TILE_SIZE));
        tiles.add(new Tile(132, 320, TILE_SIZE));
        tiles.add(new Tile( 95, 320, TILE_SIZE));
        tiles.add(new Tile( 57, 320, TILE_SIZE));

        tiles.add(new Tile( 20, 320, TILE_SIZE));
        tiles.add(new Tile( 20, 283, TILE_SIZE));
        tiles.add(new Tile( 20, 245, TILE_SIZE));

        tiles.add(new Tile( 57, 245, TILE_SIZE));
        tiles.add(new Tile( 95, 245, TILE_SIZE));
        tiles.add(new Tile(132, 245, TILE_SIZE));
        tiles.add(new Tile(170, 245, TILE_SIZE));
        tiles.add(new Tile(207, 245, TILE_SIZE));

        tiles.add(new Tile(245, 207, TILE_SIZE));
        tiles.add(new Tile(245, 170, TILE_SIZE));
        tiles.add(new Tile(245, 132, TILE_SIZE));
        tiles.add(new Tile(245,  95, TILE_SIZE));
        tiles.add(new Tile(245,  57, TILE_SIZE));

        tiles.add(new Tile(245,  20, TILE_SIZE));
        tiles.add(new Tile(283,  20, TILE_SIZE));
        tiles.add(new Tile(320,  20, TILE_SIZE));


        // RED FINISH TILES:
        tiles.add(new Tile(283,  57, TILE_SIZE));
        tiles.add(new Tile(283,  95, TILE_SIZE));
        tiles.add(new Tile(283, 132, TILE_SIZE));
        tiles.add(new Tile(283, 170, TILE_SIZE));
        tiles.add(new Tile(283, 207, TILE_SIZE));
        tiles.add(new Tile(283, 245, TILE_SIZE));

        // BLUE FINISH TILES:
        tiles.add(new Tile(508, 283, TILE_SIZE));
        tiles.add(new Tile(471, 283, TILE_SIZE));
        tiles.add(new Tile(433, 283, TILE_SIZE));
        tiles.add(new Tile(395, 283, TILE_SIZE));
        tiles.add(new Tile(358, 283, TILE_SIZE));
        tiles.add(new Tile(320, 283, TILE_SIZE));

        // YELLOW FINISH TILES:
        tiles.add(new Tile(283, 508, TILE_SIZE));
        tiles.add(new Tile(283, 471, TILE_SIZE));
        tiles.add(new Tile(283, 433, TILE_SIZE));
        tiles.add(new Tile(283, 395, TILE_SIZE));
        tiles.add(new Tile(283, 358, TILE_SIZE));
        tiles.add(new Tile(283, 320, TILE_SIZE));

        // GREEN FINISH TILES:
        tiles.add(new Tile( 57, 282, TILE_SIZE));
        tiles.add(new Tile( 95, 282, TILE_SIZE));
        tiles.add(new Tile(132, 282, TILE_SIZE));
        tiles.add(new Tile(170, 282, TILE_SIZE));
        tiles.add(new Tile(207, 282, TILE_SIZE));
        tiles.add(new Tile(245, 282, TILE_SIZE));

        // RED HOME BASE TILES:
        tiles.add(new Tile(452,  76, TILE_SIZE));
        tiles.add(new Tile(414, 113, TILE_SIZE));
        tiles.add(new Tile(490, 113, TILE_SIZE));
        tiles.add(new Tile(452, 151, TILE_SIZE));

        // BLUE HOME BASE TILES:
        tiles.add(new Tile(452, 414, TILE_SIZE));
        tiles.add(new Tile(414, 452, TILE_SIZE));
        tiles.add(new Tile(490, 452, TILE_SIZE));
        tiles.add(new Tile(452, 490, TILE_SIZE));

        // YELLOW HOME BASE TILES:
        tiles.add(new Tile(113, 414, TILE_SIZE));
        tiles.add(new Tile(76,  452, TILE_SIZE));
        tiles.add(new Tile(151, 452, TILE_SIZE));
        tiles.add(new Tile(113, 490, TILE_SIZE));

        // GREEN HOME BASE TILES:
        tiles.add(new Tile(113,  76, TILE_SIZE));
        tiles.add(new Tile(76,  113, TILE_SIZE));
        tiles.add(new Tile(151, 113, TILE_SIZE));
        tiles.add(new Tile(113, 151, TILE_SIZE));

        tile = new ArrayList<>();    // PlayerTiles
        tile.add(new ArrayList<>()); // RED
        tile.add(new ArrayList<>()); // BLUE
        tile.add(new ArrayList<>()); // YELLOW
        tile.add(new ArrayList<>()); // GREEN

        // RED POINT OF VIEW:
        for (int i=76; i<80; i++) tile.get(Faction.RED.getIndex()).add(i); // BASE
        for (int j=0; j<52; j++)  tile.get(Faction.RED.getIndex()).add(j); // SHARED
        tile.get(Faction.RED.getIndex()).add(0);                           // SHARED (back to start)
        for (int k=52; k<58; k++) tile.get(Faction.RED.getIndex()).add(k); // FINISH

        // BLUE POINT OF VIEW:
        for (int i=80; i<84; i++) tile.get(Faction.BLUE.getIndex()).add(i);   // BASE
        for (int j=0; j<39; j++)  tile.get(Faction.BLUE.getIndex()).add(j+13); // SHARED
        for (int j=0; j<13; j++)  tile.get(Faction.BLUE.getIndex()).add(j);    // SHARED
        tile.get(Faction.BLUE.getIndex()).add(13);                            // SHARED (back to start)
        for (int k=58; k<64; k++) tile.get(Faction.BLUE.getIndex()).add(k);   // FINISH

        // YELLOW POINT OF VIEW:
        for (int i=84; i<88; i++) tile.get(Faction.YELLOW.getIndex()).add(i);   // BASE
        for (int j=0; j<26; j++)  tile.get(Faction.YELLOW.getIndex()).add(j+26); // SHARED
        for (int j=0; j<26; j++)  tile.get(Faction.YELLOW.getIndex()).add(j); // SHARED
        tile.get(Faction.YELLOW.getIndex()).add(26);                            // SHARED (back to start)
        for (int k=64; k<70; k++) tile.get(Faction.YELLOW.getIndex()).add(k);   // FINISH

        // GREEN POINT OF VIEW:
        for (int i=88; i<92; i++) tile.get(Faction.GREEN.getIndex()).add(i);   // BASE
        for (int j=0; j<13; j++)  tile.get(Faction.GREEN.getIndex()).add(j+39); // SHARED
        for (int j=0; j<39; j++)  tile.get(Faction.GREEN.getIndex()).add(j); // SHARED
        tile.get(Faction.GREEN.getIndex()).add(39);                            // SHARED (back to start)
        for (int k=70; k<76; k++) tile.get(Faction.GREEN.getIndex()).add(k);   // FINISH

        ImageIcon tempBoard = new ImageIcon(getClass().getResource("/img/board.jpg"));
        boardSize = new Dimension(600, 590);
        board = tempBoard.getImage();

        /*
        for (int i=0; i<tile.get(3).size(); i++) {
            System.out.println(i + " " + tile.get(3).get(i));
        }
        */

        demo();
    }

    private void demo() {

        Token token = new Token(Faction.RED, 0);   //  Create a RED token.
        Token redToken = new Token(Faction.RED, 4);
        Token blueToken = new Token(Faction.BLUE, 0);
        Token green1Token = new Token(Faction.GREEN, 20);
        Token green2Token = new Token(Faction.GREEN, 30);
        int redId = Faction.RED.getIndex();     //  REDs enum int value.
        int tileId = tile.get(redId).get(0);    //  TileId for reds first tile.
                                            //  Tile setup: (Its the same for all PLAYERS!! (factions)
                                            //       0-3   = base tiles
                                            //       4-52  = shared tiles
                                            //       53-57 = finish tiles
        tiles.get(tileId).addToken(token);  //  Add a token to a tile!!
        tiles.get(tile.get(Faction.BLUE.getIndex()).get(0)).addToken(blueToken);
        tiles.get(tile.get(Faction.RED.getIndex()).get(4)).addToken(redToken);
        tiles.get(tile.get(Faction.GREEN.getIndex()).get(20)).addToken(green1Token);
        tiles.get(tile.get(Faction.GREEN.getIndex()).get(30)).addToken(green2Token);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawImage(board, 0, 0, null, this); // image is 600, 600.

        tiles.forEach(t -> {
            t.draw(g2d);
            g2d.setColor(Color.WHITE);
            g2d.drawString(String.valueOf(tiles.indexOf(t)),t.getX() + 12, t.getY() + 22);

        });

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(boardSize);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(boardSize);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int dice = GuiManager.getSideTopPanel().getDicePanel().getValue();

        Faction player = Faction.RED; //Get current player id from backend (0-3) 0 = red... (red begins..)

        Tile tt = tiles.stream().filter(tile -> tile.clicked(e.getX(), e.getY())).findFirst().orElse(null);
        if (tt != null  &&  tt.getPosition() != -1) {
            //System.out.println("tokenPosition " + tt.getPosition() + " for red");
            Faction check = tt.getFaction();
            if (check != null  &&  check == player) {
                System.out.println("Your RED token at " + tt.getPosition());

                int target = tt.getPosition();  // target is at current position.
                if (tt.getPosition() < 4) {
                    if (dice == 6) target = 4;  // Token can leave base. (or stay at home: see line above)
                } else {
                    target = tt.getPosition() + dice; // target is dice tiles from current position.
                }

                System.out.println("target = " + target);

                int temp = tt.getPosition() + 1;

                boolean isBlocked = false;
                while (!isBlocked  &&  temp < target) {
                    isBlocked = tiles.get(tile.get(player.getIndex()).get(temp++)).isBlocked(player);
                }

                if (isBlocked) {
                    target = temp - 2;  // the tile behind the blockade.
                }

                Token move = tt.remove();   // remove from current tile.
                move.setPosition(target);   // reset a tokens position !
                Token backToBase = tiles.get(tile.get(player.getIndex()).get(target)).addToken(move);  // place token to new tile.

                if (backToBase != null) {
                    int home = getBaseTilePosition(backToBase);
                    tiles.get(home).addToken(backToBase);
                }

                repaint();
            }

        }
    }

    private int getBaseTilePosition(Token token) {
        for (int i=0; i<4; i++) {

            System.out.println("faction " + token.getFaction());
            System.out.println("tileId " + tile.get(token.getFaction().getIndex()).get(i) + "?");

            int basePosition = tile.get(token.getFaction().getIndex()).get(i);

            System.out.println("base " + i);
            if (tiles.get(basePosition).isEmpty()) {
                return basePosition;
            }
        }
        return -1;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
