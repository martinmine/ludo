package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.GameStartedMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Martin on 22.11.2014.
 */
public class GameStartedMessageHandler implements MessageHandler {
    public void handle(GameStartedMessage message, CommunicationContext context) {
        GuiManager.getSideTopPanel().getDicePanel().setValue(0);
        GuiManager.getSideTopPanel().getDicePanel().repaint();
    }
}
