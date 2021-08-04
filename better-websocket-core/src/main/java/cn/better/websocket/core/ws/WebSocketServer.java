package cn.better.websocket.core.ws;

import cn.better.websocket.core.configuration.BetterWebSocketConfiguration;
import cn.better.websocket.core.exception.WebSocketServerException;
import cn.better.websocket.core.properties.BetterWebSocketProperties;
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
 * @date 2021-8-4
 * @remark
 */
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private ServerBootstrap serverBootstrap;

    private EventLoopGroup parentGroup;

    private EventLoopGroup childGroup;

    private Integer port;

    public WebSocketServer(BetterWebSocketProperties properties) {
        parentGroup = new NioEventLoopGroup(properties.getParentGroupThreadNumber());
        childGroup = new NioEventLoopGroup(properties.getChildGroupThreadNumber());

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketInitializer(properties));
        port = properties.getServerPort();
    }

    public void start() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(() -> {
            try {
                log.info("starting WebSocketServer...");
                ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
                log.info("started WebSocketServer");
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
