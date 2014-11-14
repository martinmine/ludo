package no.hig.imt3281.ludo.client.networking;

import no.hig.imt3281.ludo.client.messaging.MessageHandlingService;
import no.hig.imt3281.ludo.messaging.Message;
import no.hig.imt3281.ludo.messaging.MessageFactory;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.ConnectivityNotifier;
import no.hig.imt3281.ludo.messaging.handling.InvalidMessageHandlerException;
import no.hig.imt3281.ludo.messaging.handling.MissingMessageHandlerException;

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
        //this.connection = new Socket(hostname, port);
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
            } catch (IOException | InvalidMessageHandlerException | MissingMessageHandlerException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                close();
                break;
            } catch (InvocationTargetException e) {
                Throwable innerException = e.getCause();
                LOGGER.log(Level.WARNING, innerException.getMessage(), innerException);
                close();
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

        try {
            MessageFactory.serialize(message, connection.getOutputStream());
            connection.getOutputStream().flush();
        } catch (IOException e) {
            close();
            throw e;
        }
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
            // TODO: Handle exception
        }

        // TODO: Notify listeners
    }

    @Override
    public int getReferenceToken() {
        return 0;
    }

    @Override
    public void setReferenceToken(int object) {

    }
}
