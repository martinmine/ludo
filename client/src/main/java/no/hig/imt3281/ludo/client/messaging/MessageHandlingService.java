package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.messaging.*;
import no.hig.imt3281.ludo.messaging.handling.MessageHandlerFactory;

/**
 *  Registers relation between the messages and their respective handlers
 */
public class MessageHandlingService extends MessageHandlerFactory {
    public MessageHandlingService() {
        super();
        registerResponse(InitializePlayerTokenMessage.class, new InitializePlayerTokenMessageHandler());
        registerResponse(GameChallengeMessage.class, new GameChallengeMessageHandler());
        registerResponse(ChallengeableUser.class, new ChallengeableUserHandler());
        registerResponse(CreateChatRoomResult.class, new CreateChatRoomResultHandler());
        registerResponse(LoginResult.class, new LoginResultHandler());
        registerResponse(RegistrationResult.class, new RegistrationResultHandler());
        registerResponse(GroupChatMessage.class, new GroupChatMessageHandler());
        registerResponse(GlobalChatMessage.class, new GlobalChatMessageHandler());
        registerResponse(GameChatMessage.class, new GameChatMessageHandler());
        registerResponse(TriggerDiceResult.class, new TriggerDiceResultMessageHandler());
        registerResponse(GameChallengeResponse.class, new GameChallengeResponseHandler());
        registerResponse(GameEndMessage.class, new GameEndMessageHandler());
        registerResponse(GameStartedMessage.class, new GameStartedMessageHandler());
        registerResponse(MoveTokenResult.class, new MoveTokenResultHandler());
        registerResponse(TokenMovedMessage.class, new TokenMovedMessageHandler());
        registerResponse(TurnMessage.class, new TurnMessageHandler());
        registerResponse(UserEnteredGameMessage.class, new UserEnteredGameMessageHandler());
    }
}
