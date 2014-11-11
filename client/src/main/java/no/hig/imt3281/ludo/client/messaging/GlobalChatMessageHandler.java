package no.hig.imt3281.ludo.client.messaging;

import no.hig.imt3281.ludo.client.gui.GuiManager;
import no.hig.imt3281.ludo.messaging.GlobalChatMessage;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;

/**
 * Created by Joakim on 11.11.2014.
 */
public class GlobalChatMessageHandler {
    public void handle(GlobalChatMessage message, CommunicationContext context) {
        GuiManager.getChatPanel();
    }
}
