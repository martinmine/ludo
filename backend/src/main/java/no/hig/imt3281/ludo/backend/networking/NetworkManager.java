package no.hig.imt3281.ludo.backend.networking;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.xml.XmlFrameDecoder;

import java.util.logging.Logger;

/**
 * Manages incoming network connections and holds the main socket listener.
 */
public class NetworkManager {
    private static final Logger LOGGER = Logger.getLogger(NetworkManager.class.getName());

    private final int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ChannelFuture channel;
    /**
     * Prepares a new network manager.
     * @param port Port number to accept connections from
     */
    public NetworkManager(final int port) {
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        this.port = port;

    }

    /**
     * Starts listening for connections.
     */
    public void startListening() {
        LOGGER.info("Preparing to listen on port " + port);
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                LOGGER.info("Accepted connection from " + ch.remoteAddress().toString());
                ClientConnection connection = new ClientConnection(ch);
                ch.pipeline().addLast(new XmlFrameDecoder(6000), connection);
            }
            })
            .option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true);

        this.channel = b.bind(port);
        LOGGER.info("Waiting for connections");
    }

    /**
     * Destroys the network manager and discards all activity
     */
    public void destroy() {
        this.workerGroup.shutdownGracefully();
        this.bossGroup.shutdownGracefully();
        this.channel.channel().closeFuture();
    }
}
