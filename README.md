# better-websocket
基于Netty的WebSocket实现

### 使用方法
#### maven 引入
```xml
    <dependency>
        <groupId>cn.better.websocket</groupId>
        <artifactId>better-websocket-core</artifactId>
        <version>${version}</version>
    </dependency>
```

#### yml配置
```yaml
# zookeeper单节点
spring:
  better:
    websocket:
      server:
        # websocket服务启动端口 默认10000
        serverPort: 10001
        # childGroup数量 默认0
        childGroupThreadNumber: 1000
        # parentGroup数量 默认0
        parentGroupThreadNumber: 0
      connect:
        # 路径前缀 默认 ''
        pathPrefix: /websocket
        # 消息最大长度 默认1MB
        maxContentLength: 10MB
        # 读取消息最大超时时间，单位ms，小于0不设置超时 默认60000ms
        wsReadTimeout: 60000
        # 写入消息最大超时时间，单位ms，小于0不设置超时 默认60000ms
        wsWriteTimeout: 60000
```

#### 注解
```java
/**
 * 如果pathPrefix设置的 /websocket
 * 此端点的访问路径为 /websocket/chat_online
 * 此类必须实现接口 WebSocketEndpointInterface
 * websocket地址上的参数可通过WebSocketSession.getParam获得
 * 发送消息可过sendMessage方法
 */
@WebSocketMapping("/chat_online")
public class ChatOnline implements WebSocketEndpointInterface {
    /**
     * 
     */
    private static final Logger log = LoggerFactory.getLogger(ChatOnline.class);
    @Override
    public void onOpen(WebSocketSession session) {
        log.info("onOpen({}) {}", session, JSONObject.toJSONString(session));
    }

    @Override
    public void onMessage(WebSocketSession session, String message) {
        log.info("onMessage({}) {} \n[{}]", session, JSONObject.toJSONString(session), message);
        // 发送消息
        sendMessage(session, "chat_online: " + message);
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
```