package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.networking.ClientConnection;
import no.hig.imt3281.ludo.messaging.Message;

public interface MessageHandler {
    public void handle(Message message, ClientConnection context);
}
