package no.hig.imt3281.ludo.messaging;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Responsible for the creation and the end of the life-span of Message objects.
 * In other words, this class is responsible for life and death.
 */
public class MessageFactory extends Message {

    private static MessageFactory instance;

    private MessageFactory() {
    }

    /**
     * Gets the instance of the message factory.
     * @return The message factory.
     */
    public static MessageFactory getInstance() {
        if (instance == null)
            instance = new MessageFactory();
        return instance;
    }

    /**
     * Reads a Message object from a stream.
     * @param stream Stream to read the object from.
     * @return The Message object that was in the stream.
     */
    public Message deserialize(InputStream stream) {
        XMLDecoder decoder = new XMLDecoder(stream);
            return (Message)decoder.readObject();
    }

    /**
     * Serializes an object to a String
     * @param message Message to write.
     */
    public String serialize(Message message) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(output);
        xmlEncoder.writeObject(message);
        xmlEncoder.close();

        return output.toString();
    }
}
