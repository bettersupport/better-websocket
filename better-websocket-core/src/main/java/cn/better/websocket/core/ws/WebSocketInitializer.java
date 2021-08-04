package cn.better.websocket.core.ws;

import cn.better.websocket.core.properties.BetterWebSocketProperties;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

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

    }
}
