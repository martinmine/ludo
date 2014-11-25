package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.game.Game;
import no.hig.imt3281.ludo.messaging.TriggerDiceRequest;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.util.logging.Logger;

/**
 * Created by Martin on 14.11.2014.
 */
public class TriggerDiceRequestMessageHandler implements MessageHandler {
    private static final Logger LOGGER = Logger.getLogger(TriggerDiceRequestMessageHandler.class.getSimpleName());

    public void handle(TriggerDiceRequest request, CommunicationContext context) {
        User user = ServerEnvironment.getUserManager().getUser(context.getReferenceToken());
        Game game = ServerEnvironment.getGameManager().getGame(user.getCurrentGameId());

        if (game == null) {
            return;
        }

        if (game.getCurrentFactionTurn() != user.getId()) {
            LOGGER.warning("User with id " + user.getId() + " current turn is " + game.getCurrentFactionTurn());
        } else if (!game.diceTriggered()) {
            game.triggerDice();
        }
    }
}
