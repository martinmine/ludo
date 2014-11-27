package no.hig.imt3281.ludo.client.gui.game;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.MoveTokenRequest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Joakim on 27.10.2014.
 *
 * GamePanel is the Board which maps all tiles.
 * Controlling drawing the map as well as mouseClick event
 * and handling game logic received from a backend as a message.
 */
public class GamePanel extends JComponent {
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

    /**
     * Mapping the entire board, setting up all tiles
     * with their unique coordinates and size.
     * SonarQube complained about Magic numbers, which there is alot of here.
     */
    public GamePanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isLoading) {
                    // Get correct legal tile. (correct player and token on tile):
                    Tile tt = tiles.stream()
                            .filter(tile -> tile.clicked(e.getX(), e.getY()))
                            .findFirst()
                            .orElse(null);

                    // Clicked a tile AND it is token(s) on it:
                    if (tt != null  &&  !tt.isEmpty()) {

                        if (tt.getFaction().getIndex() == currentPlayer) {

                            MoveTokenRequest request = new MoveTokenRequest();
                            request.setTokenId(tt.getTokenID());

                            try {
                                Main.getServerConnection().sendMessage(request);
                            } catch (IOException ex) {
                                Main.getServerConnection().close(ex);
                            }
                        }
                    }
                }
            }
        });
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

        ImageIcon tempBoard = new ImageIcon(getClass().getResource("/img/board.png"));
        boardSize = new Dimension(600, 590);
        board = tempBoard.getImage();

        ImageIcon tempLoading = new ImageIcon(getClass().getResource("/img/ludo_loader.gif"));
        loading = tempLoading.getImage();

    }

    /**
     * The start for drawing the entire board with players token.
     * each tile has their own draw method to illustrate blockades
     * as numbers.
     * @param g The graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // image is 600, 600.
        g2d.drawImage(board, 0, 0, null, this);

        tiles.forEach(t -> t.draw(g2d));

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

    /**
     * Setting the preferred size.
     * @return Dimension obj.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(boardSize);
    }

    /**
     * Setting the MinimumSize.
     * @return Dimension obj.
     */
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(boardSize);
    }

    /**
     * Getting the current player. For feedback panel.
     * @return Int the current players id.
     */
    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Handling waiting for other players, giving feedback to the user.
     * @param load Boolean for locking mouse click events and displaying feedback.
     */
    public void setLoading(boolean load) {
        isLoading = load;
    }

    /**
     * Setting up board when players enter the game
     * @param faction int The color of the player joined.
     */
    public void joinTable(int faction) {
        LOGGER.info("User with faction " + faction + " entered the game");

        if (numPlayer < MAX_PLAYERS) {

            Faction color = Faction.RED;
            switch (faction) {
                case 0:
                    color = Faction.RED;
                    break;
                case 1:
                    color = Faction.BLUE;
                    break;
                case 2:
                    color = Faction.YELLOW;
                    break;
                case 3:
                    color = Faction.GREEN;
            }

            currentPlayer = numPlayer++;
            players[currentPlayer] = new Player(color);
            players[currentPlayer].setTokens(tiles);
            repaint();
        }
    }

    /**
     * Moving a token from tile to tile.
     * @param playerId int To get tokens current position.
     * @param tokenId int To get correct Token from player.
     * @param target int Destination for where to move the Token.
     */
    public void moveToken(int playerId, int tokenId, int target) {
        Token currentToken = players[playerId].getToken(tokenId);
        int currentPosition = players[playerId].getTokenPosition(tokenId);
        int targetPosition = players[playerId].getTileIndex(target);

        if (currentToken.getPosition() != target) {

            Token move = tiles.get(currentPosition).remove();
            move.setPosition(target);

            tiles.get(targetPosition).addToken(move);
            repaint();
        }
    }

    /**
     * Resets the game panel
     */
    public void clearTable() {
        for (Tile tile : this.tiles) {
            tile.clear();
        }

        for (int i = 0; i < MAX_PLAYERS; i++) {
            players[i] = null;
        }

        this.currentPlayer = 0;
        this.numPlayer = 0;

        repaint();
    }

    /**
     * Retrieve the current players from backend.
     * @param faction int player id / color.
     */
    public void setCurrentPlayerFaction(int faction) {
        currentPlayer = faction;
    }

    /**
     * Kicking a player token back to base. Triggered on capture.
     * @param enemyFactionId int Player id, the owner of the token.
     * @param tokenId int the token to be throwen.
     */
    public void kickPlayerToken(int enemyFactionId, int tokenId) {
        int enemyHomePosition = getEmptyBasePosition(enemyFactionId);
        int homeMapPosition = players[enemyFactionId].getTileIndex(enemyHomePosition);
        int currentMapPosition = players[enemyFactionId].getTokenPosition(tokenId);

        Token backToBase = tiles.get(currentMapPosition).remove();
        backToBase.setPosition(enemyHomePosition);

        tiles.get(homeMapPosition).addToken(backToBase);
    }

    /**
     * Get the first empty tile in a players base
     * A empty tile will always exists on kicking one back to base.
     * @param factionId int player faction id.
     * @return int The map position of the tile.
     */
    private int getEmptyBasePosition(int factionId) {
        for (int i=0; i<4; i++) {
            int basePosition = players[factionId].getTileIndex(i);
            if (tiles.get(basePosition).isEmpty()) {
                return i;
            }
        }
        return -1;
    }
}
