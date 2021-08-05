package cn.better.websocket.core.configuration;

import cn.better.websocket.core.exception.WebSocketServerException;
import cn.better.websocket.core.properties.BetterWebSocketProperties;
import cn.better.websocket.core.ws.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wang.wencheng
 * @date 2021-8-4
 * @remark
 */
@Configuration
@EnableConfigurationProperties(BetterWebSocketProperties.class)
public class BetterWebSocketConfiguration {

    @Autowired
    private BetterWebSocketProperties properties;

    @Bean
    public void initWebSocketServer() {

        WebSocketServer socketServer = new WebSocketServer(properties);
        try {
            socketServer.start();
        } catch (Exception e) {
            throw new WebSocketServerException(e);
        }

    }
}
