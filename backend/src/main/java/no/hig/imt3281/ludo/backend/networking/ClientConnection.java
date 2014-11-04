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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages client data/communication interaction
 */
public class ClientConnection extends ChannelHandlerAdapter {
    private static final Logger LOGGER = Logger.getLogger(ClientConnection.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try (ByteBufInputStream is = new ByteBufInputStream((ByteBuf) msg)) {
            Message message = MessageFactory.getInstance().deserialize(is);

            LOGGER.info("Requesting message handler for " + message.getClass().getTypeName());
            MessageHandler handler = MessageHandlerFactory.getInstance().getHandler(message.getClass());
            handler.handle(message, this);
        } catch (Exception ex) {
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
