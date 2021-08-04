package cn.better.websocket.core.configuration;

import cn.better.websocket.core.properties.BetterWebSocketProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wang.wencheng
 * @date 2021-8-4
 * @remark
 */
@Configuration
@EnableConfigurationProperties(BetterWebSocketProperties.class)
public class BetterWebSocketConfiguration {
}
