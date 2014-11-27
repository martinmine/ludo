package no.hig.imt3281.ludo.messaging.handling;

/**
 * Message handler interface, each class implementing this interface should
 * implement a function void handle([the concrete message type], CommunicationContext)
 * for handling the message type. Defining an invalid message handler would cause
 * a InvalidMessageHandlerException to be thrown.
 */
public interface MessageHandler {
}
