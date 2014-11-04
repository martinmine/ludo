package no.hig.imt3281.ludo.backend.networking;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import no.hig.imt3281.ludo.backend.message.handling.MessageHandlerFactory;
import no.hig.imt3281.ludo.backend.message.handling.MessageHandler;
import no.hig.imt3281.ludo.messaging.Message;
import no.hig.imt3281.ludo.messaging.MessageFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages client data/communication interaction
 */
public class ClientConnection extends ChannelHandlerAdapter {
    private static final Logger LOGGER = Logger.getLogger(ClientConnection.class.getName());

    /**
     * Called each time a parseable message has been received.
     * Note: This function does also use dark magic (which some call reflection)
     * @param ctx Channel data containing information about the connection
     * @param msg Message object containing received message
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try (ByteBufInputStream is = new ByteBufInputStream((ByteBuf) msg)) {
            Message message = MessageFactory.getInstance().deserialize(is);

            // Find the concrete type for the received request object
            Class<?> concreteMessageType = Class.forName(message.getClass().getTypeName());

            LOGGER.info("Requesting message handler for " + message.getClass().getTypeName());
            MessageHandler handler = MessageHandlerFactory.getInstance().getHandler(concreteMessageType);

            if (handler == null) {
                LOGGER.severe("No handler registered for " + message.getClass().getTypeName());
            }
            else {
                // Find the concrete type for the message handler
                Class<?> concreteHandlerType = Class.forName(handler.getClass().getTypeName());

                // Find the method and call the method
                Method method = concreteHandlerType.getMethod("handle", concreteMessageType, this.getClass());
                method.invoke(handler, message, this);
            }

        } catch (InvocationTargetException ex) {
            Throwable innerException = ex.getCause();
            LOGGER.log(Level.WARNING, innerException.getMessage(), innerException);
        }
        catch (Throwable ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            // TODO ???
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.log(Level.WARNING, cause.getMessage(), cause);
        ctx.close();
        // TODO close connection and notify listeners
    }
}
