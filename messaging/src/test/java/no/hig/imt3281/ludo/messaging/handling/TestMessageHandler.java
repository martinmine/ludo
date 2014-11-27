package no.hig.imt3281.ludo.messaging.handling;

/**
 * Created by marti_000 on 27.11.2014.
 */
public class TestMessageHandler implements MessageHandler {
    private int intValue;
    private String stringValue;
    private boolean boolValue;

    public void handle(TestMessage message, CommunicationContext context) {
        this.intValue = message.getIntValue();
        this.stringValue = message.getStringValue();
        this.boolValue = message.isBoolValue();
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public boolean isBoolValue() {
        return boolValue;
    }
}
