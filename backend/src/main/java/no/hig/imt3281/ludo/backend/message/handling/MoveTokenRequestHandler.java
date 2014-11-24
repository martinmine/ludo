package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.game.Game;
import no.hig.imt3281.ludo.messaging.MoveTokenRequest;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.util.logging.Logger;

/**
 * Created by Martin on 22.11.2014.
 */
public class MoveTokenRequestHandler implements MessageHandler {
    private static final Logger LOGGER = Logger.getLogger(MoveTokenRequestHandler.class.getSimpleName());

    public void handle(MoveTokenRequest message, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());
        Game game = ServerEnvironment.getGameManager().getGame(user.getCurrentGameId());

        LOGGER.info("Player " + user.getUsername() + " requesting to move token " + message.getTokenId());
        if (game.getCurrentFactionTurn() == user.getId() && game.diceTriggered()) {
            LOGGER.info("approved");
            game.moveToken(message.getTokenId());
        } else {
            LOGGER.info("Can't move that token, not your turn, current turn uid " + game.getCurrentFactionTurn() +
                    " your uid is " + user.getId());
        }
    }
}