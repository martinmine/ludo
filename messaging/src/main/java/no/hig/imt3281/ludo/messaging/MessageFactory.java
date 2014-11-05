package no.hig.imt3281.ludo.messaging;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Responsible for the creation and the end of the life-span of Message objects.
 * In other words, this class is responsible for life and death.
 */
public class MessageFactory {

    /**
     * Reads a Message object from a stream.
     * @param stream Stream to read the object from.
     * @return The Message object that was in the stream.
     */
    public static Message deserialize(InputStream stream) {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(stream));
        return (Message)decoder.readObject();
    }

    /**
     * Serializes an object to a String
     * @param message Message to write.
     */
    public static String serialize(Message message) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(output);
        xmlEncoder.writeObject(message);
        xmlEncoder.close();

        // This must be trimmed because XMLEncoder will add an \n at the end of the object
        // which messes up the parsing in some XML parsers (in this case, Netty's XML decoder)
        return output.toString().trim();
    }
}
