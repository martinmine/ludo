package no.hig.imt3281.ludo.messaging;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Responsible for the creation and the end of the life-span of Message objects.
 * In other words, this class is responsible for life and death.
 */
public class MessageFactory {

    private MessageFactory() { }

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
     * Serializes a Message objects to an output stream.
     * @param message Message to serialize
     * @param os Stream to write message to
     * @throws IOException Throws if stream is closed
     */
    public static void serialize(Message message, OutputStream os) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            try (XMLEncoder xmlEncoder = new XMLEncoder(output)) {
                xmlEncoder.writeObject(message);
            }

            byte[] data = output.toByteArray();

            // Ignore the \n at the end of the document because we don't like that
            os.write(data, 0, output.size() - 1);
        }
    }
}
