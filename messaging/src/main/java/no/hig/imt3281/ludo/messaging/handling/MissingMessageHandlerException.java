package no.hig.imt3281.ludo.messaging.handling;

/**
 * Created by Martin on 14.11.2014.
 */
public class MissingMessageHandlerException extends Exception {
    public MissingMessageHandlerException(String message, Exception cause) {
        super(message, cause);
    }
}
