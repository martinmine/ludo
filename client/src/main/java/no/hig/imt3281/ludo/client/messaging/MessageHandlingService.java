package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.messaging.*;
import no.hig.imt3281.ludo.messaging.handling.MessageHandlerFactory;

/**
 *
 */
public class MessageHandlingService extends MessageHandlerFactory {
    public MessageHandlingService() {
        super();
        registerResponse(InitializePlayerTokenMessage.class, new InitializePlayerTokenMessageHandler());
        registerResponse(ListChallengeableUsersRequest.class, new ListChallengeableUsersHandler());
        registerResponse(CreateChatRoomResult.class, new CreateChatRoomResultHandler());
        registerResponse(LoginResult.class, new LoginResultHandler());
        registerResponse(RegistrationResult.class, new RegistrationResultHandler());
        registerResponse(GroupChatMessage.class, new GroupChatMessageHandler());
        registerResponse(GlobalChatMessage.class, new GlobalChatMessageHandler());
        registerResponse(GameChatMessage.class, new GameChatMessageHandler());
        registerResponse(TriggerDiceResult.class, new TriggerDiceResultMessageHandler());
    }
}
