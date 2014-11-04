package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.messaging.LoginRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Martin on 04.11.2014.
 */
public class MessageHandlerFactory {
    private Map<Class<?>, MessageHandler> handlerMap;

    private static MessageHandlerFactory instance;

    public static MessageHandlerFactory getInstance() {
        if (instance == null)
            instance = new MessageHandlerFactory();
        return instance;
    }

    public MessageHandlerFactory() {
        this.handlerMap = new HashMap<>();

        registerResponse(LoginRequest.class, new LoginRequestHandler());
    }

    private void registerResponse(Class<?> request, MessageHandler response) {
        handlerMap.put(request, response);
    }

    public MessageHandler getHandler(Class<?> concreteType) {
        return handlerMap.get(concreteType);
    }
}
