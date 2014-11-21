package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.InitializePlayerTokenMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

/**
 * Created by Thomas on 21.11.2014.
 */
public class InitializePlayerTokenMessageHandler implements MessageHandler {

    public void handler(InitializePlayerTokenMessage message, CommunicationContext context) {
        GuiManager.getGamePanel().joinTable(message.getPlayerId());
    }
}
