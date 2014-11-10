package no.hig.imt3281.ludo.client.gui;

import no.hig.imt3281.ludo.client.Main;

import javax.swing.*;

/**
 * Created by Joakim on 05.11.2014.
 */
public class MenuBar extends JMenuBar {
    public MenuBar() {

        JMenu fileMenu = new JMenu(Main.resourceBundle.getString("MENUBAR_TITLE_NAME_FILE"));
        fileMenu.setMnemonic("F".charAt(0));

        add(fileMenu);
    }
}
