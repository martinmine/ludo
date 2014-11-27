package no.hig.imt3281.ludo.messaging.handling;

/**
 * Notifier listener which is listening for events when the connection is closed.
 */
public interface ConnectivityNotifier {
    /**
     * The connection is closed, cleanup resources.
     */
    public void connectionClosed();
}
