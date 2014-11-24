package no.hig.imt3281.ludo.messaging.handling;

import no.hig.imt3281.ludo.messaging.Message;

import java.io.IOException;

/**
 *
 */
public interface CommunicationContext {
    void sendMessage(Message msg) throws IOException;
    void setStatusListener(ConnectivityNotifier listener);
    void close();
    void close(Throwable cause);
    int getReferenceToken();
    void setReferenceToken(int object);
}
