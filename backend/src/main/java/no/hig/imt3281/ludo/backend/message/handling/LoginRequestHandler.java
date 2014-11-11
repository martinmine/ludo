package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.LoginRequest;
import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Martin on 04.11.2014.
 */
public class LoginRequestHandler implements MessageHandler {
    private static final Logger LOGGER = Logger.getLogger(LoginRequestHandler.class.getSimpleName());

    public void handle(LoginRequest request, CommunicationContext context) {
        LOGGER.info("User with username " + request.getUsername() + " requesting login");

        LoginResult response = new LoginResult();

        User user = ServerEnvironment.getUserManager().getUser(request.getUsername(), request.getPassword());
        if (user == null) {
            response.setResultCode(LoginResult.INVALID_CREDENTIALS);
        }
        else {
            response.setResultCode(LoginResult.OK);
            ServerEnvironment.getUserManager().setLoggedIn(user);
        }

        try {
            context.sendMessage(response);
        } catch (IOException e) {
            context.close();
        }
    }
}
