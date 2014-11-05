package no.hig.imt3281.ludo.client.gui.layer.game;

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

    public static final int RED = 0;
    public static final int BLUE = 1;
    public static final int YELLOW = 2;
    public static final int GREEN = 3;
    public static final int MAX_PLAYERS = 4;
    public static final int FACTIONS[] = {RED, BLUE, YELLOW, GREEN };

    private Dimension boardSize;
    private Image board;
    //private ArrayList<Tiles> tiles;
    private ArrayList<Integer> playerTiles[];

    public GamePanel() {

        playerTiles = new ArrayList[48];

        ImageIcon tempBoard = new ImageIcon(getClass().getResource("/img/board2.jpg"));
        boardSize = new Dimension(640, 640);
        board = tempBoard.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawImage(board, 0, 0, null, this);
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
