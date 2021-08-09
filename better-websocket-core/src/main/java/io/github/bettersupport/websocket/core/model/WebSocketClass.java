package io.github.bettersupport.websocket.core.model;

import io.github.bettersupport.websocket.core.annotation.WebSocketMapping;

/**
 * @author wang.wencheng
 * @date 2021-8-4
 * @remark
 */
public class WebSocketClass {

    private WebSocketMapping webSocketMapping;

    private Class<?> clazz;

    public WebSocketClass(WebSocketMapping webSocketMapping, Class<?> clazz) {
        this.webSocketMapping = webSocketMapping;
        this.clazz = clazz;
    }

    public WebSocketMapping getWebSocketMapping() {
        return webSocketMapping;
    }

    public void setWebSocketMapping(WebSocketMapping webSocketMapping) {
        this.webSocketMapping = webSocketMapping;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
