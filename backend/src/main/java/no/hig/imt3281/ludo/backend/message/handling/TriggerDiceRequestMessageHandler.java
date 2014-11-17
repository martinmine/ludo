package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.messaging.TriggerDiceRequest;
import no.hig.imt3281.ludo.messaging.TriggerDiceResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Martin on 14.11.2014.
 */
public class TriggerDiceRequestMessageHandler implements MessageHandler {
    private static final Random RANDOM = new Random();
    private static final int DICE_MAX = 6;
    private static final Logger LOGGER = Logger.getLogger(TriggerDiceRequestMessageHandler.class.getSimpleName());

    public void handle(TriggerDiceRequest request, CommunicationContext context) {
        TriggerDiceResult response = new TriggerDiceResult();

        response.setDiceValue(RANDOM.nextInt(DICE_MAX));

        try {
            context.sendMessage(response);
        } catch (IOException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }
    }
}
