package no.hig.imt3281.ludo.backend.message.handling;

import no.hig.imt3281.ludo.backend.ServerEnvironment;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.messaging.RegistrationRequest;
import no.hig.imt3281.ludo.messaging.RegistrationResult;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.handling.MessageHandler;

import java.io.IOException;

/**
 * Created by Martin on 11.11.2014.
 */
public class RegistrationRequestHandler implements MessageHandler {
    public void handle(RegistrationRequest request, CommunicationContext context) {
        RegistrationResult response = new RegistrationResult();

        if (ServerEnvironment.getUserManager().getUser(request.getUsername()) != null) {
            response.setResult(RegistrationResult.USERNAME_TAKEN);
        }
        else if (request.getUsername().length() == 0) {
            response.setResult(RegistrationResult.INVALID_USERNAME);
        }
        else if (!request.getEmail().contains("@")) {
            response.setResult(RegistrationResult.INVALID_MAIL);
        }
        else if (request.getPassword().length() < 5) {
            response.setResult(RegistrationResult.WEAK_PASSWORD);
        }
        else {
            User user = new User(request.getUsername(), request.getEmail());

            try {
                user.setPassword(ServerEnvironment.getUserManager().hashPassword(request.getPassword()));
                ServerEnvironment.getUserManager().registerUser(user);
                response.setResult(RegistrationResult.OK);
            } catch (Exception e) {
                response.setResult(RegistrationResult.SERVER_ERROR);
            }
        }

        try {
            context.sendMessage(response);
        } catch (IOException e) {
            context.close();
        }
    }
}
