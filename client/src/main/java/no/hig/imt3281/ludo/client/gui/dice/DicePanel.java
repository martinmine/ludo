package no.hig.imt3281.ludo.client.gui.dice;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joakim on 05.11.2014.
 */
public class DicePanel extends JComponent {

    private Image[] dice;

    public DicePanel() {
        dice = new Image[6];

        for (int i = 0; i < 6; i++) {
            ImageIcon temp = new ImageIcon(getClass().getResource("/img/dice" + (i+1) + ".gif"));

            dice[i] = temp.getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawImage(dice[5],0,0,160,160,this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(160,160);
    }
}
