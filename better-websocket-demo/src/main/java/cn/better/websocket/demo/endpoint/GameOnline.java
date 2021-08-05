package cn.better.websocket.demo.endpoint;

import cn.better.websocket.core.WebSocketEndpointInterface;
import cn.better.websocket.core.annotation.WebSocketMapping;
import cn.better.websocket.core.model.WebSocketSession;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
@WebSocketMapping("/game_online")
public class GameOnline implements WebSocketEndpointInterface {
    private static final Logger log = LoggerFactory.getLogger(GameOnline.class);
    @Override
    public void onOpen(WebSocketSession session) {
        log.info("onOpen {}", JSONObject.toJSONString(session));
    }

    @Override
    public void onMessage(WebSocketSession session, String message) {
        log.info("onMessage {} \nmsg {}", JSONObject.toJSONString(session), message);
        sendMessage(session, "game_online: " + message);
    }

    @Override
    public void onClose(WebSocketSession session) {
        log.info("onClose {}", JSONObject.toJSONString(session));
    }

    @Override
    public void onError(WebSocketSession session, Throwable t) {
        log.info("onError {}", JSONObject.toJSONString(session));
        log.info("onError {} | {}", JSONObject.toJSONString(session), Throwables.getStackTraceAsString(t));
    }
}
