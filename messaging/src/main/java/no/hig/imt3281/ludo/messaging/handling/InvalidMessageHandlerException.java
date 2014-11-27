package no.hig.imt3281.ludo.messaging.handling;

/**
 * Exception thrown when a handler is invoked that does not have an appropriate
 * method signature for handling the concrete message type.
 */
public class InvalidMessageHandlerException extends Exception {
    public InvalidMessageHandlerException(String message, Exception cause) {
        super(message, cause);
    }
}
