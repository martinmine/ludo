package no.hig.imt3281.ludo.messaging.handling;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Tests the invoke part of the messaging library.
 */
public class MessageHandlerFactoryTest {
    private static final int NUMBER = 20;
    private static final String WORD = "Test";
    private static final boolean CONDITION = true;
    private static final Logger LOGGER = Logger.getLogger(MessageHandlerFactoryTest.class.getSimpleName());

    private MessageHandlerFactory factory;
    private TestMessageHandler testHandler;

    @Before
    public void setUp() {
        this.factory = new MessageHandlerFactory();
        this.testHandler = new TestMessageHandler();
        this.factory.registerResponse(TestMessage.class, testHandler);
    }

    @Test
    public void testInvokeMessage() {
        TestMessage message = new TestMessage();
        message.setIntValue(NUMBER);
        message.setStringValue(WORD);
        message.setBoolValue(CONDITION);

        try {
            factory.invokeMessage(message, null);
        } catch (MissingMessageHandlerException | InvalidMessageHandlerException | InvocationTargetException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            assertTrue(false);
        }

        assertEquals(message.getIntValue(), testHandler.getIntValue());
        assertEquals(message.getStringValue(), testHandler.getStringValue());
        assertEquals(message.isBoolValue(), testHandler.isBoolValue());

        assertEquals(message.getIntValue(), NUMBER);
        assertEquals(message.getStringValue(), WORD);
        assertEquals(message.isBoolValue(), CONDITION);
    }
}