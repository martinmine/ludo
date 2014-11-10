package no.hig.imt3281.ludo.client;

import no.hig.imt3281.ludo.client.gui.Client;
import no.hig.imt3281.ludo.client.networking.ServerConnection;
import no.hig.imt3281.ludo.messaging.LoginRequest;

import javax.swing.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joakim on 27.10.2014.
 *
 */
public class Main {
    public static final String LANG_PATH = "i18n.strings";
    public static ResourceBundle resourceBundle;
    private static ServerConnection serverConnection;

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Connecting");

        /*
        try {
            serverConnection = new ServerConnection("localhost", 9494);
        } catch (IOException e) {
            LOGGER.severe("Unable to connect to server");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            System.exit(0);
        }
        */
        LOGGER.info("Connected");

        // Sample code
        LoginRequest request = new LoginRequest();
        request.setUsername("ChipM");
        request.setPassword("a");

        /*
        try {
            LOGGER.info("Sending request");
            serverConnection.sendMessage(request);
            LOGGER.info("Request sent");
        } catch (IOException e) {
            LOGGER.severe("Unable to send login request");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            System.exit(0);
        }
        */
        // Sample code end

        setUserPreferences();
        new Client();
    }

    public static ServerConnection getServerConnection() {
        return serverConnection;
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
