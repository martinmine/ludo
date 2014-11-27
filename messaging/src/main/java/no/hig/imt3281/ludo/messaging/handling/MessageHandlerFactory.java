package no.hig.imt3281.ludo.messaging.handling;

import no.hig.imt3281.ludo.messaging.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Factory for message handlers, receives messages and invokes the appropriate message handler.
 * Does also contain a registry of the message handlers.
 */
public class MessageHandlerFactory {
    private static final Logger LOGGER = Logger.getLogger(MessageHandlerFactory.class.getSimpleName());
    private Map<Class<?>, MessageHandler> handlerMap;

    /**
     * Prepares a new message handler factory.
     */
    public MessageHandlerFactory() {
        this.handlerMap = new HashMap<>();
    }

    /**
     * Register a message handler.
     * @param request Type of the incoming message.
     * @param response The message handler to invoke for the incoming message.
     */
    protected void registerResponse(Class<?> request, MessageHandler response) {
        handlerMap.put(request, response);
    }

    private MessageHandler getHandler(Class<?> concreteType) {
        return handlerMap.get(concreteType);
    }

    /**
     * Attempts to invoke a message handler for an incoming message.
     * @param message The incoming message.
     * @param context Reference to the connection that received the message.
     * @throws MissingMessageHandlerException No handler exist for this type.
     * @throws InvalidMessageHandlerException Invalid or missing handle method signature.
     * @throws InvocationTargetException Exception was thrown while handling the message.
     */
    public void invokeMessage(Message message, CommunicationContext context)
            throws MissingMessageHandlerException, InvalidMessageHandlerException, InvocationTargetException {
        try {
            // Find the concrete type for the received request object
            Class<?> concreteMessageType = Class.forName(message.getClass().getTypeName());

            LOGGER.info("Requesting message handler for " + message.getClass().getTypeName());
            MessageHandler handler = getHandler(concreteMessageType);

            if (handler == null) {
                LOGGER.severe("No handler registered for " + message.getClass().getTypeName());
            } else {
                // Find the concrete type for the message handler
                Class<?> concreteHandlerType = Class.forName(handler.getClass().getTypeName());

                // Find the method and call the method
                Method method = concreteHandlerType.getMethod("handle", concreteMessageType, CommunicationContext.class);
                method.invoke(handler, message, context);
            }
        } catch (ClassNotFoundException ex) {
            throw new MissingMessageHandlerException("Missing handler for message " + message.getClass().getTypeName(), ex);
        } catch (NoSuchMethodException | IllegalAccessException ex) {
            throw new InvalidMessageHandlerException("Invalid signature for handler for " + message.getClass().getTypeName(), ex);
        }
    }
}
