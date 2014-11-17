package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.messaging.*;
import no.hig.imt3281.ludo.messaging.handling.MessageHandlerFactory;

/**
 *
 */
public class MessageHandlingService extends MessageHandlerFactory {
    public MessageHandlingService() {
        super();

        registerResponse(LoginRequest.class, new LoginRequestHandler());
        registerResponse(CreateChatRoomRequest.class, new CreateChatRoomRequestHandler());
        registerResponse(GameChatMessage.class, new GameChatMessageHandler());
        registerResponse(GlobalChatMessage.class, new GlobalChatMessageHandler());
        registerResponse(GroupChatMessage.class, new GroupChatMessageHandler());
        registerResponse(RegistrationRequest.class, new RegistrationRequestHandler());
        registerResponse(TriggerDiceRequest.class, new TriggerDiceRequestMessageHandler());
        registerResponse(ListChallengeableUsersRequest.class, new ListChallengeableUserRequestHandler());
    }
}
