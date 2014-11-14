package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.messaging.TriggerDiceResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 14.11.2014.
 */
public class TriggerDiceResultMessageHandler implements MessageHandler {
    public void handle(TriggerDiceResult result, CommunicationContext context) {
        int value = result.getDiceValue();
        // TODO: Do stuff with value and notify GUI
    }
}
