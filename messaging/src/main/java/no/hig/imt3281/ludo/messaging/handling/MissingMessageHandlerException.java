package no.hig.imt3281.ludo.messaging.handling;

/**
 * Exception thrown when there are no message handler for an incoming message.
 */
public class MissingMessageHandlerException extends Exception {
    public MissingMessageHandlerException(String message, Exception cause) {
        super(message, cause);
    }
}
