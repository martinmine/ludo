package no.hig.imt3281.ludo.messaging.handling;

import no.hig.imt3281.ludo.messaging.Message;

/**
 * Created by marti_000 on 27.11.2014.
 */
public class TestMessage extends Message {
    private int intValue;
    private String stringValue;
    private boolean boolValue;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public boolean isBoolValue() {
        return boolValue;
    }

    public void setBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
    }
}
