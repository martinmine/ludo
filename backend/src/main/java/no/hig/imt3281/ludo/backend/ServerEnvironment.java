package no.hig.imt3281.ludo.backend;

import no.hig.imt3281.ludo.backend.chat.ChatManager;
import no.hig.imt3281.ludo.backend.game.GameManager;
import no.hig.imt3281.ludo.backend.game.queue.GameQueueManager;
import no.hig.imt3281.ludo.backend.networking.ClientConnection;
import no.hig.imt3281.ludo.backend.networking.NetworkManager;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Core class holding references to different services in the program
 */
public class ServerEnvironment {
    private static final Logger LOGGER = Logger.getLogger(ClientConnection.class.getName());
    private static final int SERVER_PULSE = 100;
    private static final long SECOND = 1000L;
    public static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";

    private static NetworkManager networkManager;
    private static SessionFactory sessionFactory;
    private static UserManager userManager;
    private static ChatManager chatManager;
    private static GameManager gameManager;
    private static GameQueueManager gameQueueManager;
    private static Thread cycleThread;
    private static boolean isAlive = true;
    private static Properties properties = new Properties();

    private ServerEnvironment() {
    }
    /**
     * Initializes the server environment
     */
    public static void initialize() {
        try (FileInputStream inputStream = new FileInputStream("config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            return;
        }

        try {
            // This call makes sure we have the jdbc MySQL driver loaded
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            LOGGER.severe("Unable to start the main environment, no SQL driver found");
            return;
        }

        // Setup Hibernate
        Configuration configuration = new Configuration();
        configuration.configure();
        String configString = configuration.getProperty(HIBERNATE_CONNECTION_URL);

        configString = configString.replace("${env.LUDO_DB_HOST}", properties.getProperty("LUDO_DB_HOST"));
        configString = configString.replace("${env.LUDO_DB_PORT}", properties.getProperty("LUDO_DB_PORT"));
        configString = configString.replace("${env.LUDO_DB_NAME}", properties.getProperty("LUDO_DB_NAME"));

        configuration.setProperty(HIBERNATE_CONNECTION_URL, configString);
        configuration.setProperty("hibernate.connection.username", properties.getProperty("LUDO_DB_USERNAME"));
        configuration.setProperty("hibernate.connection.password", properties.getProperty("LUDO_DB_PASSWORD"));

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        networkManager = new NetworkManager(Integer.valueOf(properties.getProperty("GAME_PORT")));
        userManager = new UserManager();
        chatManager = new ChatManager();
        gameManager = new GameManager();
        gameQueueManager = new GameQueueManager();

        cycleThread = new Thread(() -> {
           while (isAlive) {
               userManager.onCycle();
               chatManager.onCycle();
               gameQueueManager.onCycle();
               gameManager.onCycle();

               try {
                   Thread.sleep(SERVER_PULSE);
               } catch (InterruptedException e) {
                   LOGGER.log(Level.SEVERE, e.getMessage(), e);
               }
           }
        });

        cycleThread.start();
    }

    public static NetworkManager getNetworkManager() {
        return networkManager;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public static ChatManager getChatManager() {
        return chatManager;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static GameQueueManager getGameQueueManager() {
        return gameQueueManager;
    }

    public static int getCurrentTimeStamp() {
        return (int) (System.currentTimeMillis() / SECOND);
    }

    public static String getPasswordSalt() {
        return "<insert long random string here that should have been in a resource file>";
    }
}
