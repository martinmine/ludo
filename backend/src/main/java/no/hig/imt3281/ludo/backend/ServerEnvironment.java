package no.hig.imt3281.ludo.backend;

import no.hig.imt3281.ludo.backend.chat.ChatManager;
import no.hig.imt3281.ludo.backend.networking.ClientConnection;
import no.hig.imt3281.ludo.backend.networking.NetworkManager;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Core class holding references to different services in the program
 */
public class ServerEnvironment {
    private static final Logger LOGGER = Logger.getLogger(ClientConnection.class.getName());

    private static NetworkManager networkManager;
    private static SessionFactory sessionFactory;
    private static UserManager userManager;
    private static ChatManager chatManager;
    private static Thread cycleThread;
    private static boolean isAlive = true;

    private ServerEnvironment() { }
    /**
     * Initializes the server environment
     */
    public static void initialize() {
        try {
            // This call makes sure we have the jdbc MySQL driver loaded
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            LOGGER.severe("Unable to start the main environment, no SQL driver found");
        }

        // Setup Hibernate
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        networkManager = new NetworkManager(9494);
        userManager = new UserManager();
        chatManager = new ChatManager();

        cycleThread = new Thread(() -> {
           while (isAlive) {
               userManager.onCycle();
               try {
                   Thread.sleep(100);
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

    public static int getCurrentTimeStamp() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public static String getPasswordSalt() {
        return "<insert long random string here that should have been in a resource file>";
    }
}
