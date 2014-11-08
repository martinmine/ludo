package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.messaging.LoginRequest;
import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.MessageContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.io.IOException;

/**
 * Created by Martin on 04.11.2014.
 */
public class LoginRequestHandler implements MessageHandler {
    public void handle(LoginRequest request, MessageContext context) {
        System.out.println("The username: " + request.getUsername());
        System.out.println("The password: " + request.getPassword());

        LoginResult response = new LoginResult();
        response.setResultCode(LoginResult.INVALID_PASSWORD);
        try {
            context.sendMessage(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
