package no.hig.imt3281.ludo.backend.networking;

import io.netty.buffer.*;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import no.hig.imt3281.ludo.backend.User;
import no.hig.imt3281.ludo.backend.message.handling.MessageHandlingService;
import no.hig.imt3281.ludo.messaging.handling.ConnectivityNotifier;
import no.hig.imt3281.ludo.messaging.handling.CommunicationContext;
import no.hig.imt3281.ludo.messaging.Message;
import no.hig.imt3281.ludo.messaging.MessageFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages client data/communication interaction
 */
public class ClientConnection extends ChannelHandlerAdapter implements CommunicationContext {
    private static final Logger LOGGER = Logger.getLogger(ClientConnection.class.getName());
    private static final MessageHandlingService messageHandler = new MessageHandlingService();
    private SocketChannel socketChannel;
    private ConnectivityNotifier statusListener;
    private int referenceToken;

    public ClientConnection(SocketChannel ch) {
        this.socketChannel = ch;
    }

    /**
     * Sends a message to the connection
     * @param msg The message
     */
    public void sendMessage(Message msg) throws IOException {
        ByteBufAllocator alloc = PooledByteBufAllocator.DEFAULT;
        ByteBuf buf = alloc.buffer(1024);

        try (ByteBufOutputStream outputStream = new ByteBufOutputStream(buf)) {
            MessageFactory.serialize(msg, outputStream);
            // The XMLDecoder wants a \0 at the end of the document, so we add that
            // to make the document readable for it so it becomes useful.
            outputStream.writeChar('\0');
            this.socketChannel.writeAndFlush(buf);
        } catch (IOException ex) {
            LOGGER.log(Level.INFO, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public void setStatusListener(ConnectivityNotifier listener) {
        this.statusListener = listener;
    }

    /**
     * Called each time a parseable message has been received.
     * Note: This function does also use dark magic (which some call reflection)
     * @param ctx Channel data containing information about the connection
     * @param msg Message object containing received message
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf)msg;
        try (ByteBufInputStream is = new ByteBufInputStream(buf)) {
            Message message = MessageFactory.deserialize(is);
            messageHandler.invokeMessage(message, this);
        } catch (InvocationTargetException ex) {
            Throwable innerException = ex.getCause();
            LOGGER.log(Level.WARNING, innerException.getMessage(), innerException);
            close();
        } catch (Throwable ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            close();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.log(Level.WARNING, cause.getMessage(), cause);
        close();
    }

    public void close() {
        this.socketChannel.close();
        if (this.statusListener != null) {
            this.statusListener.connectionClosed();
        }
    }

    @Override
    public int getReference() {
        return referenceToken;
    }

    @Override
    public void setReference(int token) {
        this.referenceToken = token;
    }
}
