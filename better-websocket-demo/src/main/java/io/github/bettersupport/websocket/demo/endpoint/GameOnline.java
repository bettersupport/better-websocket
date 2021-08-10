package io.github.bettersupport.websocket.demo.endpoint;

import io.github.bettersupport.websocket.core.WebSocketEndpointInterface;
import io.github.bettersupport.websocket.core.annotation.WebSocketMapping;
import io.github.bettersupport.websocket.core.model.WebSocketSession;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wang.wencheng
 * date 2021-8-3
 * describe
 */
@WebSocketMapping("/game_online")
public class GameOnline implements WebSocketEndpointInterface {
    private static final Logger log = LoggerFactory.getLogger(GameOnline.class);
    @Override
    public void onOpen(WebSocketSession session) {
        log.info("onOpen({}) {}", session, JSONObject.toJSONString(session));
    }

    @Override
    public void onMessage(WebSocketSession session, String message) {
        log.info("onMessage({}) {} \n[{}]", session, JSONObject.toJSONString(session), message);
        sendMessage(session, "game_online: " + message);
    }

    @Override
    public void onClose(WebSocketSession session) {
        log.info("onClose({}) {}", session, JSONObject.toJSONString(session));
    }

    @Override
    public void onError(WebSocketSession session, Throwable t) {
        log.error("onError({}) {} | {}", session, JSONObject.toJSONString(session), Throwables.getStackTraceAsString(t));
    }
}
