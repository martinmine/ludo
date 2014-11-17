package no.hig.imt3281.ludo.client.gui.dice;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.TriggerDiceRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Joakim on 05.11.2014.
 *
 */
public class DicePanel extends JComponent implements MouseListener {

    private static final int MAX = 6;
    private static final int MIN = 0;

    private Image[] dice;
    private int face;

    public DicePanel() {
        addMouseListener(this);
        dice = new Image[6];

        for (int i = 0; i < 6; i++) {
            ImageIcon temp = new ImageIcon(getClass().getResource("/img/dice" + (i+1) + ".gif"));
            dice[i] = temp.getImage();
        }
    }

    public int getValue() {
        return this.face + 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawImage(dice[face],0,0,160,160,this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(160,160);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Random rand = new Random();
        //face = rand.nextInt(MAX) + MIN;
        //repaint();

        TriggerDiceRequest request = new TriggerDiceRequest();
        try {
            Main.getServerConnection().sendMessage(request);
        } catch (IOException ex) {
            ex.printStackTrace();
            // TODO: make gui close and notify the user the the connection was closed
        }
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

    public void setValue(final int value) {
        this.face = value;
        repaint();
    }
}
