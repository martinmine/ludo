package no.hig.imt3281.ludo.messaging.handling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the invoke part of the messaging library.
 */
public class MessageHandlerFactoryTest {
    private static final int NUMBER = 20;
    private static final String WORD = "Test";
    private static final boolean CONDITION = true;

    private MessageHandlerFactory factory;
    private TestMessageHandler testHandler;

    @Before
    public void setUp() {
        this.factory = new MessageHandlerFactory();
        this.testHandler = new TestMessageHandler();
        this.factory.registerResponse(TestMessage.class, testHandler);
    }

    @Test
    public void testInvokeMessage() throws Exception {
        TestMessage message = new TestMessage();
        message.setIntValue(NUMBER);
        message.setStringValue(WORD);
        message.setBoolValue(CONDITION);

        factory.invokeMessage(message, null);

        assertEquals(message.getIntValue(), testHandler.getIntValue());
        assertEquals(message.getStringValue(), testHandler.getStringValue());
        assertEquals(message.isBoolValue(), testHandler.isBoolValue());

        assertEquals(message.getIntValue(), NUMBER);
        assertEquals(message.getStringValue(), WORD);
        assertEquals(message.isBoolValue(), CONDITION);
    }
}