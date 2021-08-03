package cn.better.websocket.demo.endpoint;

import cn.better.websocket.core.WebSocketEndpointInterface;
import cn.better.websocket.core.annotation.WebSocketMapping;
import cn.better.websocket.core.model.WebSocketSession;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
@WebSocketMapping("/chat_online")
public class ChatOnline implements WebSocketEndpointInterface {
    @Override
    public void onOpen(WebSocketSession session) {

    }

    @Override
    public void onMessage(WebSocketSession session) {

    }

    @Override
    public void onClose(WebSocketSession session) {

    }

    @Override
    public void onError(WebSocketSession session, Throwable t) {

    }
}
