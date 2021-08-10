package io.github.bettersupport.websocket.core.ws;

import io.github.bettersupport.websocket.core.properties.BetterWebSocketProperties;
import com.google.common.base.Throwables;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wang.wencheng
 * date 2021-8-4
 * describe
 */
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private ServerBootstrap serverBootstrap;

    private EventLoopGroup parentGroup;

    private EventLoopGroup childGroup;

    private Integer port;

    public WebSocketServer(BetterWebSocketProperties properties) {
        parentGroup = new NioEventLoopGroup(properties.getServer().getParentGroupThreadNumber());
        childGroup = new NioEventLoopGroup(properties.getServer().getChildGroupThreadNumber());

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketInitializer(properties));
        port = properties.getServer().getServerPort();
    }

    public void start() throws InterruptedException {
        log.info("Starting WebSocketServer...");
        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        log.info("Started WebSocketServer on port {}", port);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> {
            try {
                channelFuture.channel().closeFuture().sync();
            } catch (Exception e) {
                log.error("WebSocketServer:ERROR: {}", Throwables.getStackTraceAsString(e));
            } finally {
                parentGroup.shutdownGracefully();
                childGroup.shutdownGracefully();
            }
        });

    }
}
