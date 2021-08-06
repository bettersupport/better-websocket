package cn.better.websocket.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

/**
 * @author wang.wencheng
 * @date 2021-8-4
 * @remark
 */
@ConfigurationProperties(prefix = "spring.better.websocket")
public class BetterWebSocketProperties {

    /**
     * 服务配置
     */
    private Server server = new Server();

    /**
     * 连接配置
     */
    private Connect connect = new Connect();

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Connect getConnect() {
        return connect;
    }

    public void setConnect(Connect connect) {
        this.connect = connect;
    }

    public static class Server {
        /**
         * 父组线程数
         */
        private Integer parentGroupThreadNumber = 0;

        /**
         * 子组线程数
         */
        private Integer childGroupThreadNumber = 0;

        /**
         * 服务端口
         */
        private Integer serverPort = 10000;

        public Integer getParentGroupThreadNumber() {
            return parentGroupThreadNumber;
        }

        public void setParentGroupThreadNumber(Integer parentGroupThreadNumber) {
            this.parentGroupThreadNumber = parentGroupThreadNumber;
        }

        public Integer getChildGroupThreadNumber() {
            return childGroupThreadNumber;
        }

        public void setChildGroupThreadNumber(Integer childGroupThreadNumber) {
            this.childGroupThreadNumber = childGroupThreadNumber;
        }

        public Integer getServerPort() {
            return serverPort;
        }

        public void setServerPort(Integer serverPort) {
            this.serverPort = serverPort;
        }
    }

    public static class Connect {
        /**
         * 路径前缀 格式 /***
         */
        private String pathPrefix = "";

        /**
         * 读数据超时时间
         */
        private Long wsReadTimeout = 60000L;

        /**
         * 写数据超时时间
         */
        private Long wsWriteTimeout = 60000L;

        /**
         * 消息体最大长度，默认1MB
         */
        private DataSize maxContentLength = DataSize.ofMegabytes(1);

        public String getPathPrefix() {
            return pathPrefix;
        }

        public void setPathPrefix(String pathPrefix) {
            this.pathPrefix = pathPrefix;
        }

        public Long getWsReadTimeout() {
            return wsReadTimeout;
        }

        public void setWsReadTimeout(Long wsReadTimeout) {
            this.wsReadTimeout = wsReadTimeout;
        }

        public Long getWsWriteTimeout() {
            return wsWriteTimeout;
        }

        public void setWsWriteTimeout(Long wsWriteTimeout) {
            this.wsWriteTimeout = wsWriteTimeout;
        }

        public DataSize getMaxContentLength() {
            return maxContentLength;
        }

        public void setMaxContentLength(DataSize maxContentLength) {
            this.maxContentLength = maxContentLength;
        }
    }
}
