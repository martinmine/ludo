package no.hig.imt3281.ludo.client.gui.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Joakim on 27.10.2014.
 *
 */
public class GamePanel extends JComponent implements MouseListener {

    //private static final Logger LOGGER = Logger.getLogger(GamePanel.class.getName());

    private Dimension boardSize;
    private Image board;
    private ArrayList<Tile> tiles;
    private ArrayList<ArrayList<Integer>> tile;

    public GamePanel() {

        tiles = new ArrayList<>();
        tiles.add(new Tile(113, 75, 30));

        tile = new ArrayList<>();    // PlayerTiles
        tile.add(new ArrayList<>()); // RED
        tile.add(new ArrayList<>()); // BLUE
        tile.add(new ArrayList<>()); // YELLOW
        tile.add(new ArrayList<>()); // GREEN

        ImageIcon tempBoard = new ImageIcon(getClass().getResource("/img/board.jpg"));
        boardSize = new Dimension(600, 590);
        board = tempBoard.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawImage(board, 0, 0, null, this); // image is 600, 600.
        tiles.forEach(t -> t.draw(g2d));

        //tile.forEach(t -> t.draw(g2d));

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
