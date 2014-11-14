package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.LoginRequest;
import no.hig.imt3281.ludo.messaging.LoginResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Martin on 04.11.2014.
 */
public class LoginRequestHandler implements MessageHandler {
    private static final Logger LOGGER = Logger.getLogger(LoginRequestHandler.class.getSimpleName());

    public void handle(LoginRequest request, CommunicationContext context) {
        LOGGER.info("User with username " + request.getUsername() + " requesting login");

        LoginResult response = new LoginResult();

        try {
            User user = ServerEnvironment.getUserManager().getUser(request.getUsername(), request.getPassword());
            if (user == null) {
                response.setResultCode(LoginResult.INVALID_CREDENTIALS);
            } else {
                context.setReferenceToken(user.getId());
                response.setResultCode(LoginResult.OK);
                user.setClientConnection(context);
                ServerEnvironment.getUserManager().setLoggedIn(user);
            }
        } catch (Exception e) {
            response.setResultCode(LoginResult.SERVER_ERROR);
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        try {
            context.sendMessage(response);
        } catch (IOException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            context.close();
        }
    }
}
