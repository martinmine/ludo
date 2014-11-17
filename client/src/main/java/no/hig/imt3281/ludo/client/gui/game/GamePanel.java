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

        // Player tiles - referencing with int (index).
        tile = new ArrayList<>();

        // Red is first index = 0;
        tile.add(new ArrayList<>());

        // Blue is second index = 1;
        tile.add(new ArrayList<>());

        // Yellow is third index = 2;
        tile.add(new ArrayList<>());

        // Green is 4th index = 3;
        tile.add(new ArrayList<>());

        // Red point of view BASE:
        for (int i=76; i<80; i++) {
            tile.get(Faction.RED.getIndex()).add(i);
        }

        // Red point of view SHARED:
        for (int j=0; j<52; j++)  {
            tile.get(Faction.RED.getIndex()).add(j);
        }

        // Red point of view SHARED (back to start)
        tile.get(Faction.RED.getIndex()).add(0);

        // Red point of view FINISH:
        for (int k=52; k<58; k++) tile.get(Faction.RED.getIndex()).add(k);


        // Blue poi BASE:
        for (int i=80; i<84; i++) {
            tile.get(Faction.BLUE.getIndex()).add(i);
        }

        // Blue poi SHARED #1:
        for (int j=0; j<39; j++)  {
            tile.get(Faction.BLUE.getIndex()).add(j+13);
        }

        // Blue poi SHARED #2:
        for (int j=0; j<13; j++)  tile.get(Faction.BLUE.getIndex()).add(j);

        // Blue poi SHARED (back to start):
        tile.get(Faction.BLUE.getIndex()).add(13);

        // Blue poi FINISH:
        for (int k=58; k<64; k++) {
            tile.get(Faction.BLUE.getIndex()).add(k);
        }

        // Yellow poi BASE:
        for (int i=84; i<88; i++) {
            tile.get(Faction.YELLOW.getIndex()).add(i);
        }

        // Yellow poi SHARED #1:
        for (int j=0; j<26; j++)  {
            tile.get(Faction.YELLOW.getIndex()).add(j+26);
        }

        // Yellow poi SHARED #2:
        for (int j=0; j<26; j++)  {
            tile.get(Faction.YELLOW.getIndex()).add(j);
        }

        // Yellow poi SHARED (back to start)
        tile.get(Faction.YELLOW.getIndex()).add(26);

        // Yellow poi FINISH:
        for (int k=64; k<70; k++) {
            tile.get(Faction.YELLOW.getIndex()).add(k);
        }

        // Green poi BASE:
        for (int i=88; i<92; i++) {
            tile.get(Faction.GREEN.getIndex()).add(i);
        }

        // Green poi SHARED #1:
        for (int j=0; j<13; j++)  {
            tile.get(Faction.GREEN.getIndex()).add(j+39);
        }

        // Green poi SHARED #2:
        for (int j=0; j<39; j++)  {
            tile.get(Faction.GREEN.getIndex()).add(j);
        }

        // Green poi SHARED (back to start)
        tile.get(Faction.GREEN.getIndex()).add(39);

        // Green poi FINISH:
        for (int k=70; k<76; k++) {
            tile.get(Faction.GREEN.getIndex()).add(k);
        }

        ImageIcon tempBoard = new ImageIcon(getClass().getResource("/img/board.jpg"));
        boardSize = new Dimension(600, 590);
        board = tempBoard.getImage();

        /* Debugging
        for (int i=0; i<tile.get(0).size(); i++) {
            System.out.println(i + " " + tile.get(0).get(i));
        }
        */

        demo();
    }

    /**
     * Tile setup: (Its the same for all PLAYERS!! (factions)
     * 0-3   = base tiles
     * 4-52  = shared tiles
     * 53-57 = finish tiles
     *
     *
     * DELETE THIS FUNCTION ;)
     */
    private void demo() {

        Token token = new Token(Faction.RED, 0);
        Token redToken = new Token(Faction.RED, 4);
        Token blueToken = new Token(Faction.BLUE, 0);
        Token green1Token = new Token(Faction.GREEN, 20);
        Token green2Token = new Token(Faction.GREEN, 30);

        //  REDs enum int value.
        int redId = Faction.RED.getIndex();

        //  TileId for reds first tile.
        int tileId = tile.get(redId).get(0);

        // Add a token to a tile!!
        tiles.get(tileId).addToken(token);
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

        //Get current player id from backend (0-3) 0 = red...
        Faction player = Faction.RED;

        Tile tt = tiles.stream().filter(tile -> tile.clicked(e.getX(), e.getY())).findFirst().orElse(null);
        if (tt != null  &&  tt.getPosition() != -1) {

            Faction check = tt.getFaction();
            if (check != null  &&  check == player) {

                // Calculate target out of the top Token on tile (blockade)
                int target = tt.getPosition();

                if (tt.getPosition() < 4) {

                    // Token can leave base. (or stay at home: see line above)
                    if (dice == 6) target = 4;
                } else {

                    // target is dice tiles from current position.
                    target += dice;
                }

                // current position;
                int temp = tt.getPosition();

                int blockade = isBlocked(player, temp, target);

                // If there is a blockade:
                if (blockade > 0) {
                    // move just behind blockade.
                    target = blockade - 1;
                }

                int last = tile.get(player.getIndex()).size() - 1;
                if (target > last) {
                    int diff = target - last;
                    target = last - diff;
                }

                // Remove token from current tile.
                Token move = tt.remove();

                // Reset a tokens position. (!)
                move.setPosition(target);

                // Get index to actual tile.
                int targetTileIndex = tile.get(player.getIndex()).get(target);

                // If target Tile is occupied by an enemy Token (no blockade)
                // enemy token gets kicked back to base, else returns null.
                Token backToBase = tiles.get(targetTileIndex).addToken(move);

                // Kicking an enemy Token back to base:
                if (backToBase != null) {
                    // Finding the next free home Tile
                    int home = getBaseTilePosition(backToBase);
                    tiles.get(home).addToken(backToBase);
                }

                repaint();
            }
        }
    }

    private int getBaseTilePosition(Token token) {
        for (int i=0; i<4; i++) {

            int basePosition = tile.get(token.getFaction().getIndex()).get(i);

            if (tiles.get(basePosition).isEmpty()) {
                return basePosition;
            }
        }
        return -1;
    }

    /**
     * Checks if there is any blockade between two tiles.
     *
     * @param player Faction for checking opposite tokens. You are allowed to jump over your own blockades.
     * @param currentPosition int tokens current position.
     * @param target int current position + dice value
     * @return int the position where its a blockade. You can move just behind the blockade.
     */
    private int isBlocked(Faction player, int currentPosition, int target) {
        int last = tile.get(player.getIndex()).get(tile.get(player.getIndex()).size() - 6);
        int num = (target - currentPosition) + 1;

        // No point looking for blockades on finish tiles.
        if (currentPosition >= last) {
            return 0;
        }

        boolean blocked = false;
        int i = 0;

        // i becomes number of tiles from current position to nearest blockade.
        // if no blockade is found, i becomes the target position.
        while (!blocked  &&  ++i < num) {
            int index = tile.get(player.getIndex()).get(currentPosition + i);
            blocked = tiles.get(index).isBlocked(player);
        }

        // No blockades found.
        if (i == num) {
            return 0;
        }

        return currentPosition + i;
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
