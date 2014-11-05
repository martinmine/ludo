package no.hig.imt3281.ludo.client;

import no.hig.imt3281.ludo.client.gui.layer.Client;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joakim on 27.10.2014.
 */
public class Main {
    public static final String LANG_PATH = "i18n.strings";
    public static ResourceBundle resourceBundle;

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        setUserPreferences();
        new Client();
    }

    public static void setUserPreferences() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        String languageCode = Locale.getDefault().toLanguageTag();
        Locale.setDefault(new Locale("en"));
        Locale currentLocale;
        // No default language received
        if (languageCode.length() == 0) {
            resourceBundle = ResourceBundle.getBundle(LANG_PATH);
        } else if (languageCode.length() == 2) {
            // Try to set retrieved country code
            try {
                currentLocale = new Locale(languageCode);
                resourceBundle = ResourceBundle.getBundle(LANG_PATH, currentLocale);
                System.out.println(currentLocale.toString());
            } catch(Exception e) {
                LOGGER.log(Level.FINE, e.getMessage(), e);
                resourceBundle = ResourceBundle.getBundle(LANG_PATH);
            }
            // Try to set retrieved country code & locale
        } else if (languageCode.length() == 5) {
            try {
                currentLocale = new Locale(languageCode.substring(0,2),languageCode.substring(3));
                resourceBundle = ResourceBundle.getBundle(LANG_PATH, currentLocale);
            } catch(Exception e) {
                LOGGER.log(Level.FINE, e.getMessage(), e);
                currentLocale = Locale.getDefault();
                resourceBundle = ResourceBundle.getBundle(LANG_PATH, currentLocale);
            }
        }
    }
}
