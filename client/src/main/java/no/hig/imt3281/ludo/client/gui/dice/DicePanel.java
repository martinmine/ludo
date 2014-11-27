package no.hig.imt3281.ludo.client.gui.dice;

import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.messaging.TriggerDiceRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by Joakim on 05.11.2014.
 *
 */
public class DicePanel extends JComponent {
    private static final int MAX = 7;

    private Image[] dice;
    private int face;

    public DicePanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TriggerDiceRequest request = new TriggerDiceRequest();
                try {
                    Main.getServerConnection().sendMessage(request);
                } catch (IOException ex) {
                    Main.getServerConnection().close(ex);
                }
            }
        });

        dice = new Image[MAX];

        for (int i = 0; i < MAX; i++) {
            ImageIcon temp = new ImageIcon(getClass().getResource("/img/dice" + i + ".png"));
            dice[i] = temp.getImage();
        }
    }

    public int getValue() {
        return this.face;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawImage(dice[face], 0, 0, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(160, 160);
    }

    public void setValue(final int value) {
        this.face = value;
        repaint();
    }
}
