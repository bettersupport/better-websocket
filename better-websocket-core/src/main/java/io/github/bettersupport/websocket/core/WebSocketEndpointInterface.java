package io.github.bettersupport.websocket.core;

import io.github.bettersupport.websocket.core.exception.WebSocketSendMessageException;
import io.github.bettersupport.websocket.core.model.WebSocketSession;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
public interface WebSocketEndpointInterface {

    /**
     * 连接打开
     * @param session 会话
     */
    void onOpen(WebSocketSession session);

    /**
     * 连接收到消息
     * @param session 会话
     * @param message 消息
     */
    void onMessage(WebSocketSession session, String message);

    /**
     * 连接关闭
     * @param session 会话
     */
    void onClose(WebSocketSession session);

    /**
     * 连接发生错误
     * @param session 会话
     * @param t 异常
     */
    void onError(WebSocketSession session, Throwable t);

    /**
     * 发送消息
     * @param session 会话
     * @param message 消息
     * @throws WebSocketSendMessageException 发送消息异常
     */
    default void sendMessage(WebSocketSession session, String message) throws WebSocketSendMessageException {
        if (session == null) {
            throw new NullPointerException("WebSocketSession is null");
        }
        if (session.getContext() == null || session.getContext().channel() == null) {
            throw new WebSocketSendMessageException("WebSocketSession not available");
        }
        sendMessage(session.getContext().channel(), message);
    }

    /**
     * 发送消息
     * @param channel 通道
     * @param message 消息
     * @throws WebSocketSendMessageException
     */
    default void sendMessage(Channel channel, String message) throws WebSocketSendMessageException {
        if (channel.isOpen()) {
            if (channel.isActive() && channel.isRegistered() && channel.isWritable()) {
                channel.writeAndFlush(new TextWebSocketFrame(message));
            } else {
                throw new WebSocketSendMessageException("connection not available");
            }
        } else {
            throw new WebSocketSendMessageException("connection closed");
        }
    }

}
