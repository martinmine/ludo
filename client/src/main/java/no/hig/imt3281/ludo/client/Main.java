package no.hig.imt3281.ludo.client;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * Created by Joakim on 27.10.2014.
 */
public class Main {

    public static final String I18NPATH = "i18n.strings";
    public static ResourceBundle resourceBundle;

    public static void main(String[] args) {
       // setUserPreferences(args);
        new GameClient();
    }

    public static void setUserPreferences(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        String languageCode = Locale.getDefault().toLanguageTag();

        // No default language received
        if (languageCode.length() == 0) {
            resourceBundle = ResourceBundle.getBundle(I18NPATH);
        } else if (languageCode.length() == 2) {
            // Try to set retrieved country code
            try {
                resourceBundle = ResourceBundle.getBundle(I18NPATH, new Locale(languageCode));
            } catch(Exception e) {
               // LOGGER.log(Level.FINE, e.getMessage(), e);
                resourceBundle = ResourceBundle.getBundle(I18NPATH);
            }
            // Try to set retrieved country code & locale
        } else if (languageCode.length() == 5) {
            try {
                Locale locale = new Locale(languageCode.substring(0,2),languageCode.substring(3));
                resourceBundle = ResourceBundle.getBundle(I18NPATH, locale);
            } catch(Exception e) {
                //LOGGER.log(Level.FINE, e.getMessage(), e);
                resourceBundle = ResourceBundle.getBundle(I18NPATH);
            }
        }
    }
}
