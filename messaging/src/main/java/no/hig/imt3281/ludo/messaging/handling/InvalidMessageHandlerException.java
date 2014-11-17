package no.hig.imt3281.ludo.messaging.handling;

/**
 * Created by Martin on 14.11.2014.
 */
public class InvalidMessageHandlerException extends Exception {
    public InvalidMessageHandlerException(String message, Exception cause) {
        super(message, cause);
    }
}
