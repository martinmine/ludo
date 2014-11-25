package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.game.Game;
import no.hig.imt3281.ludo.messaging.TriggerDiceRequest;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.util.logging.Logger;

/**
 * Handles requests related to when users throws the dice.
 */
public class TriggerDiceRequestMessageHandler implements MessageHandler {
    private static final Logger LOGGER = Logger.getLogger(TriggerDiceRequestMessageHandler.class.getSimpleName());

    public void handle(TriggerDiceRequest request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());
        Game game = ServerEnvironment.getGameManager().getGame(user.getCurrentGameId());

        if (game == null) {
            return;
        }

        if (game.getCurrentMovingUserId() != user.getId()) {
            LOGGER.warning("User with id " + user.getId() + " current turn is " + game.getCurrentMovingUserId());
        } else if (!game.diceTriggered()) {
            game.triggerDice();
        }
    }
}
