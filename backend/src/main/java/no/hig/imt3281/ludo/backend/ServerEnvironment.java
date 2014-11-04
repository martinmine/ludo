package no.hig.imt3281.ludo.backend;

import no.hig.imt3281.ludo.backend.networking.ClientConnection;
import no.hig.imt3281.ludo.backend.networking.NetworkManager;
import no.hig.imt3281.ludo.messaging.LoginRequest;
import no.hig.imt3281.ludo.messaging.MessageFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Core class holding references to different services in the program
 */
public class ServerEnvironment {
    private static final Logger LOGGER = Logger.getLogger(ClientConnection.class.getName());

    private static NetworkManager networkManager;
    private static SessionFactory sessionFactory;

    /**
     * Initializes the server environment
     */
    public static void initialize() {
        try {
            // This call makes sure we have the jdbc MySQL driver loaded
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex) {
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
        networkManager.startListening();

        try {
            Socket echoSocket = new Socket("localhost", 9494);

            LoginRequest request = new LoginRequest();
            request.setUsername("Martin");
            request.setPassword("foo123");

            MessageFactory.getInstance().serialize(request, echoSocket.getOutputStream());
            echoSocket.getOutputStream().flush();
            System.out.println("Sent message");
        }
        catch (Exception ex) {
            System.out.println("Error");
            System.out.println(ex.getMessage());
        }

    }

    public static NetworkManager getNetworkManager() {
        return networkManager;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /*
    public static Integer addUser(User player) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            employeeID = (Integer) session.save(player);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }
*/
}
