package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.messaging.*;
import no.hig.imt3281.ludo.messaging.handling.MessageHandlerFactory;

/**
 *
 */
public class MessageHandlingService extends MessageHandlerFactory {
    public MessageHandlingService() {
        super();

        registerResponse(EnterGameQueueMessage.class, new EnterGameQueueMessageHandler());
        registerResponse(LoginRequest.class, new LoginRequestHandler());
        registerResponse(CreateChatRoomRequest.class, new CreateChatRoomRequestHandler());
        registerResponse(GameChatMessage.class, new GameChatMessageHandler());
        registerResponse(GlobalChatMessage.class, new GlobalChatMessageHandler());
        registerResponse(GroupChatMessage.class, new GroupChatMessageHandler());
        registerResponse(RegistrationRequest.class, new RegistrationRequestHandler());
        registerResponse(TriggerDiceRequest.class, new TriggerDiceRequestMessageHandler());
        registerResponse(ListChallengeableUsersRequest.class, new ListChallengeableUserRequestHandler());
        registerResponse(ChallengeUserRequest.class, new ChallengeUserRequestHandler());
        registerResponse(GameChallengeResponse.class, new GameChallengeResponseHandler());
        registerResponse(MoveTokenRequest.class, new MoveTokenRequestHandler());
    }
}
