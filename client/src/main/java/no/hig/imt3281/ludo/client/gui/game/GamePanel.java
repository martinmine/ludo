package no.hig.imt3281.ludo.client.gui.game;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.InitializePlayerTokenMessage;
import no.hig.imt3281.ludo.messaging.MoveTokenRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joakim on 27.10.2014.
 *
 * GamePanel is the Board which maps all tiles.
 */
public class GamePanel extends JComponent implements MouseListener {
    private static final int MAX_PLAYERS = 4;
    private static final Logger LOGGER = Logger.getLogger(GamePanel.class.getSimpleName());

    private Dimension boardSize;
    private final static int TILE_SIZE = 35;
    private Image board;
    private Image loading;
    private ArrayList<Tile> tiles;
    private Player players[];
    private int numPlayer;
    private int currentPlayer;
    private boolean isLoading;

    public GamePanel() {
        addMouseListener(this);
        players = new Player[MAX_PLAYERS];
        currentPlayer = 0;
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

        ImageIcon tempBoard = new ImageIcon(getClass().getResource("/img/board.jpg"));
        boardSize = new Dimension(600, 590);
        board = tempBoard.getImage();

        ImageIcon tempLoading = new ImageIcon(getClass().getResource("/img/ludo_loader.gif"));
        loading = tempLoading.getImage();

        //demo();
    }


    /**
     * Tile setup: (Its the same for all PLAYERS!! (factions)
     * 0-3   = base tiles
     * 4-52  = shared tiles
     * 53-57 = finish tiles
     */
    private void demo() {

        currentPlayer = numPlayer++;
        players[currentPlayer] = new Player(Faction.RED);
        players[currentPlayer].setTokens(tiles);

        currentPlayer = numPlayer++;
        players[currentPlayer] = new Player(Faction.BLUE);
        players[currentPlayer].setTokens(tiles);

        currentPlayer = numPlayer++;
        players[currentPlayer] = new Player(Faction.YELLOW);
        players[currentPlayer].setTokens(tiles);

        currentPlayer = numPlayer++;
        players[currentPlayer] = new Player(Faction.GREEN);
        players[currentPlayer].setTokens(tiles);

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // image is 600, 600.
        g2d.drawImage(board, 0, 0, null, this);

        tiles.forEach(t -> {
            t.draw(g2d);
            //g2d.setColor(Color.WHITE);
            //g2d.drawString(String.valueOf(tiles.indexOf(t)),t.getX() + 12, t.getY() + 22);
        });

        if (isLoading) {
            Rectangle outOfFocus = new Rectangle(0,0,600,600);
            Composite comp = g2d.getComposite();
            AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
            g2d.setComposite(composite);
            Color color = new Color(1,1,1, 0.9f);
            g2d.setPaint(color);
            g2d.fill(outOfFocus);
            g2d.setComposite(comp);
            g2d.drawImage(loading, 250, 250, null, this);
        }

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


        if (!isLoading) {
            // get dice value. (take this from backend)...
            int dice = GuiManager.getSideTopPanel().getDicePanel().getValue();

            // the users turn...
            Faction player = Faction.RED;

            // Get correct legal tile. (correct player and token on tile):
            Tile tt = tiles.stream()
                    .filter(tile -> tile.clicked(e.getX(), e.getY())  &&  tile.getFaction() == player)
                    .findFirst()
                    .orElse(null);

            if (tt != null  &&  !tt.isEmpty()) {

                // Tile owner. Has player allowed to move this token?
                Faction check = tt.getFaction();
                if (check != null  &&  check == player) {

                    MoveTokenRequest request = new MoveTokenRequest();
                    request.setTokenId(tt.getPosition());

                    try {
                        Main.getServerConnection().sendMessage(request);
                    } catch (IOException e1) {
                        LOGGER.log(Level.WARNING, e1.getMessage(), e);
                    }

                    InitializePlayerTokenMessage initPlayerMessage = new InitializePlayerTokenMessage();
                    initPlayerMessage.setPlayerId(player.getIndex());

                    try {
                        Main.getServerConnection().sendMessage(initPlayerMessage);
                    } catch (IOException e1) {
                        LOGGER.log(Level.WARNING, e1.getMessage(), e);
                    }

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

                    int last = players[player.getIndex()].getEndTileIndex(); //tile.get(player.getIndex()).size() - 1;
                    if (target > last) {
                        int diff = target - last;
                        target = last - diff;
                    }

                    // Remove token from current tile.
                    Token move = tt.remove();

                    // Reset a tokens position. (!)
                    move.setPosition(target);

                    // Get index to actual tile.
                    int targetTileIndex = players[player.getIndex()].getTileIndex(target); //tile.get(player.getIndex()).get(target);

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
    }

    private int getBaseTilePosition(Token token) {
        for (int i=0; i<4; i++) {

            int basePosition = players[token.getFaction().getIndex()].getTileIndex(i);

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
        int last = players[currentPlayer].getStartOfFinishTileIndex();
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

            int index = players[currentPlayer].getTileIndex(currentPosition + i);
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

    public void setLoading(boolean load) {
        isLoading = load;
    }

    public void joinTable(int faction) {
        LOGGER.info("User with faction " + faction + " entered the game");
        Faction color = Faction.RED;
        switch(faction) {
            case 0: color = Faction.RED;
                break;
            case 1: color = Faction.BLUE;
                break;
            case 2: color = Faction.YELLOW;
                break;
            case 3: color = Faction.GREEN;
        }

        currentPlayer = numPlayer++;
        players[currentPlayer] = new Player(color);
        players[currentPlayer].setTokens(tiles);

        repaint();
    }

}
