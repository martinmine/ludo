package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.networking.ClientConnection;
import no.hig.imt3281.ludo.messaging.LoginRequest;
import no.hig.imt3281.ludo.messaging.Message;

/**
 * Created by Martin on 04.11.2014.
 */
public class LoginRequestHandler implements MessageHandler {
    public void handle(LoginRequest request, ClientConnection context) {
        System.out.println("The username: " + request.getUsername());
        System.out.println("The password: " + request.getPassword());
    }
}
