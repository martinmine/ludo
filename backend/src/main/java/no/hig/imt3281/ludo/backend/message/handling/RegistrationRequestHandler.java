package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.RegistrationRequest;
import no.hig.imt3281.ludo.messaging.RegistrationResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Takes care of registration requests from clients.
 */
public class RegistrationRequestHandler implements MessageHandler {
    private static final Logger LOGGER = Logger.getLogger(MessageHandler.class.getSimpleName());
    private static final int MIN_PASSWORD_LENGTH = 5;
    private static final int MIN_EMAIL_LENGTH = 5;

    public void handle(RegistrationRequest request, CommunicationContext context) {
        RegistrationResult response = new RegistrationResult();

        if (request.getUsername() == null || request.getUsername().length() == 0) {
            response.setResult(RegistrationResult.INVALID_USERNAME);
        } else if (ServerEnvironment.getUserManager().getUser(request.getUsername()) != null) {
            response.setResult(RegistrationResult.USERNAME_TAKEN);
        } else if (request.getEmail() == null || !request.getEmail().contains("@") || request.getEmail().length() < MIN_EMAIL_LENGTH) {
            response.setResult(RegistrationResult.INVALID_MAIL);
        } else if (request.getPassword() == null || request.getPassword().length() < MIN_PASSWORD_LENGTH) {
            response.setResult(RegistrationResult.WEAK_PASSWORD);
        } else {
            User user = new User(request.getUsername(), request.getEmail());

            try {
                String password = ServerEnvironment.getUserManager().hashPassword(request.getPassword());
                user.setPassword(password);
                ServerEnvironment.getUserManager().registerUser(user);
                ServerEnvironment.getUserManager().setLoggedIn(user, context);
                response.setResult(RegistrationResult.OK);
            } catch (Exception e) {
                response.setResult(RegistrationResult.SERVER_ERROR);
                LOGGER.log(Level.INFO, e.getMessage(), e);
            }
        }

        try {
            context.sendMessage(response);
        } catch (IOException e) {
            context.close(e);
        }
    }
}
