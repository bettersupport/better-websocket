package cn.better.websocket.core.model;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
public class WebSocketSession {

    private ChannelHandlerContext context;

    private Object message;

    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
