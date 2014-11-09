package no.hig.imt3281.ludo.backend;

import no.hig.imt3281.ludo.backend.networking.ClientConnection;
import no.hig.imt3281.ludo.backend.networking.NetworkManager;
import no.hig.imt3281.ludo.messaging.LoginRequest;
import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.MessageFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.*;
import java.net.Socket;
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
        /*
        try {
            Socket s = new Socket("localhost", 9494);

            //BufferedReader br = new BufferedReader (new InputStreamReader (s.getInputStream()));
            OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
            BufferedWriter bw = new BufferedWriter (os);

            //BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

            LoginRequest request = new LoginRequest();
            request.setUsername("Martin");
            request.setPassword("foo123");

            MessageFactory.serialize(request, s.getOutputStream());
            s.getOutputStream().flush();
            bw.flush();

            //bw.write("<foo>sample message</foo>\n");
            //bw.flush();
            System.out.println("Sent message");

            System.out.println("Reading object...");
            //LoginResult responseMessage = (LoginResult)MessageFactory.getInstance().deserialize(s.getInputStream());
            //System.out.println("Result was : " + responseMessage.getResultCode());

            //XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(s.getInputStream()));
            System.out.println("Reading");
            //LoginResult response = (LoginResult)decoder.readObject();
            LoginResult response = (LoginResult)MessageFactory.deserialize(s.getInputStream());
            //decoder.close();
            System.out.println("Response is " + response.getResultCode());

            LoginRequest rep = new LoginRequest();
            rep.setUsername("un");
            rep.setPassword("pw");

            MessageFactory.serialize(rep, s.getOutputStream());
            System.out.println("Sent bytes ");
            s.getOutputStream().flush();
            bw.flush();

            response = (LoginResult)MessageFactory.deserialize(s.getInputStream());
            //decoder.close();
            System.out.println("Response is " + response.getResultCode());



            //bw.write(MessageFactory.getInstance().serialize(request));
            //bw.flush();

            /*while (s.isConnected()) {
                char[] buffer = new char[500];
                int readBytes = br.read(buffer);

                String receivedText = String.valueOf(buffer);
                System.out.println("Received " +  readBytes + " bytes: " + receivedText);
            }*/
        /*
        }
        catch (Exception ex) {
            System.out.println("Error");
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }*/

    }

    public static NetworkManager getNetworkManager() {
        return networkManager;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static UserManager getUserManager() { return userManager; }
}
