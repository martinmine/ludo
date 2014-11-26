package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.AudioManager;
import no.hig.imt3281.ludo.client.Main;
import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.TriggerDiceResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Handles incoming messages of the type TriggerDiceResult
 */
public class TriggerDiceResultMessageHandler implements MessageHandler {
    public void handle(TriggerDiceResult result, CommunicationContext context) {
        int value = result.getDiceValue();
        AudioManager.playSound("diceroll.wav", false);
        GuiManager.getSideTopPanel().getDicePanel().setValue(value);

        if (result.getCurrentMovingFaction() == GuiManager.getGamePanel().getCurrentPlayer()) {
            GuiManager.getSideTopPanel().getFeedbackPanel().setText(Main.getResourceBundle().getString("PLAYER_CLICK_TOKEN"));
        }
    }
}
