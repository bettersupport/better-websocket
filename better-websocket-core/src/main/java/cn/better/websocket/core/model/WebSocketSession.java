package cn.better.websocket.core.model;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
public class WebSocketSession {

    private ChannelHandlerContext context;

    private Map<String, String> params;

    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
