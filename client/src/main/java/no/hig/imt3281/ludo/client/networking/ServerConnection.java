package no.hig.imt3281.ludo.client.networking;

import no.hig.imt3281.ludo.client.AudioManager;
import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.messaging.MessageHandlingService;
import no.hig.imt3281.ludo.messaging.Message;
import no.hig.imt3281.ludo.messaging.MessageFactory;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.ConnectivityNotifier;
import no.hig.imt3281.ludo.messaging.handling.InvalidMessageHandlerException;
import no.hig.imt3281.ludo.messaging.handling.MissingMessageHandlerException;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ServerConnection implements Runnable, CommunicationContext {
    private static final Logger LOGGER = Logger.getLogger(ServerConnection.class.getSimpleName());
    private Socket connection;
    private Thread readerThread;
    private MessageHandlingService messageHandler;

    public ServerConnection(String hostname, int port) throws IOException {
        this.connection = new Socket(hostname, port);
        this.readerThread = new Thread(this);
        this.messageHandler = new MessageHandlingService();
        this.readerThread.start();

        LOGGER.info("Connection ready");
    }

    @Override
    public void run() {
        while (connection.isConnected()) {
            try {
                LOGGER.info("Waiting for new message");
                Message message = MessageFactory.deserialize(connection.getInputStream());
                LOGGER.info("Got message: " + message.getClass().getTypeName());
                messageHandler.invokeMessage(message, this);
            } catch (IOException | InvalidMessageHandlerException | MissingMessageHandlerException | ArrayIndexOutOfBoundsException e) {
                close(e);
                break;
            } catch (InvocationTargetException e) {
                LOGGER.log(Level.WARNING, e.getMessage(), e);
                close(e.getCause());
                break;
            }
        }
    }

    /**
     * Sends a message to the server.
     * @param message Message to send.
     * @throws IOException Exception is thrown during network connection.
     */
    public void sendMessage(Message message) throws IOException {
        LOGGER.info("Sending message " + message.getClass().getTypeName());

        MessageFactory.serialize(message, connection.getOutputStream());
        connection.getOutputStream().flush();
    }

    @Override
    public void setStatusListener(ConnectivityNotifier listener) {

    }

    /**
     * Closes the server connection and notifies the listeners
     */
    public void close() {
        LOGGER.info("Closing socket");
        try {
            this.connection.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        AudioManager.playSound("disconnected.wav");
        JOptionPane.showMessageDialog(null, Main.resourceBundle.getString("DISCONNECTED"));
        System.exit(0);
    }

    /**
     * Closes the server connection
     * @param cause The exception making the connection close
     */
    public void close(Throwable cause) {
        close();
        LOGGER.log(Level.SEVERE, cause.getMessage(), cause);
    }

    @Override
    public int getReferenceToken() {
        return 0;
    }

    @Override
    public void setReferenceToken(int object) {

    }
}
