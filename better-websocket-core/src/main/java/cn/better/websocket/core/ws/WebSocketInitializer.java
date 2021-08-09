package cn.better.websocket.core.ws;

import cn.better.websocket.core.properties.BetterWebSocketProperties;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author wang.wencheng
 * @date 2021-8-4
 * @remark
 */
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {

    private BetterWebSocketProperties properties;

    public WebSocketInitializer(BetterWebSocketProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (properties.getConnect().getWsReadTimeout() >= 0) {
            pipeline.addLast(new ReadTimeoutHandler(properties.getConnect().getWsReadTimeout(), TimeUnit.MILLISECONDS));
        }

        if (properties.getConnect().getWsWriteTimeout() >= 0) {
            pipeline.addLast(new WriteTimeoutHandler(properties.getConnect().getWsWriteTimeout(), TimeUnit.MILLISECONDS));
        }

        pipeline.addLast(new HttpServerCodec());

        int maxContentLength = Long.valueOf(properties.getConnect().getMaxContentLength().toBytes()).intValue();
        pipeline.addLast(new HttpObjectAggregator(maxContentLength));

        pipeline.addLast(new WebSocketHandler(properties));

        pipeline.addLast(new WebSocketServerProtocolHandler(properties.getConnect().getPathPrefix(), true));
    }
}
