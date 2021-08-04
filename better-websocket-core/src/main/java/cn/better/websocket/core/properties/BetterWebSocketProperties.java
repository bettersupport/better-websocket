package cn.better.websocket.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wang.wencheng
 * @date 2021-8-4
 * @remark
 */
@ConfigurationProperties(prefix = "spring.better.websocket")
public class BetterWebSocketProperties {

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
    private Integer maxContentLength = 1024 * 1024;


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

    public Integer getMaxContentLength() {
        return maxContentLength;
    }

    public void setMaxContentLength(Integer maxContentLength) {
        this.maxContentLength = maxContentLength;
    }
}
