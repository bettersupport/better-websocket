package io.github.bettersupport.websocket.core.ws;

import io.github.bettersupport.websocket.core.WebSocketEndpointInterface;
import io.github.bettersupport.websocket.core.configuration.WebSocketMapRegister;
import io.github.bettersupport.websocket.core.model.WebSocketClass;
import io.github.bettersupport.websocket.core.model.WebSocketSession;
import io.github.bettersupport.websocket.core.properties.BetterWebSocketProperties;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang.wencheng
 * date 2021-8-5
 * describe
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

    private static final String UPGRADE_VALUE = "websocket";

    private WebSocketEndpointInterface endpoint;

    private BetterWebSocketProperties properties;

    private Map<String, String> params;

    private WebSocketSession session;

    public WebSocketHandler(BetterWebSocketProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        if (endpoint != null) {
            session.setContext(ctx);
            endpoint.onMessage(session, msg.text());
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg != null && msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            String upgrade = request.headers().get("Upgrade");
            if (StringUtils.isEmpty(upgrade) || !UPGRADE_VALUE.equalsIgnoreCase(upgrade)) {
                ctx.close();
            } else {
                String pathPrefix = properties.getConnect().getPathPrefix();
                if (StringUtils.isEmpty(pathPrefix)) {
                    pathPrefix = "";
                }
                if (uri.indexOf(pathPrefix) != 0) {
                    log.warn("url {} not found", uri);
                    notFoundError(ctx);
                } else {
                    String path = uri.substring(pathPrefix.length());
                    int paramStartIndex = path.indexOf("?");
                    String pathWithoutParam = path.substring(0, paramStartIndex >= 0 ? paramStartIndex : path.length());
                    WebSocketClass clazz = WebSocketMapRegister.getWebSocketMap().get(pathWithoutParam);
                    if (clazz == null) {
                        log.warn("url {} not found", path);
                        notFoundError(ctx);
                    } else {
                        super.channelRead(ctx, msg);
                        params = getParamsByURI(uri);

                        endpoint = (WebSocketEndpointInterface) clazz.getClazz().getConstructor().newInstance();

                        session = new WebSocketSession();
                        session.setContext(ctx);
                        session.setParams(params);

                        endpoint.onOpen(session);
                    }
                }
            }
        } else {
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (endpoint != null) {
            session.setContext(ctx);
            endpoint.onClose(session);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (endpoint != null) {
            session.setContext(ctx);
            endpoint.onError(session, cause);
        }
    }

    private void notFoundError(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
        ctx.writeAndFlush(response);
    }

    private Map<String, String> getParamsByURI(String uri) {
        Map<String, String> params = new HashMap<>();
        if (null != uri && uri.contains("?")) {
            String[] uriArray = uri.split("\\?");
            if (null != uriArray && uriArray.length > 1) {
                String[] paramsArray = uriArray[1].split("&");
                if (null != paramsArray && paramsArray.length >= 1) {
                    for (String param : paramsArray) {
                        String[] keyValue = param.split("=");
                        String key = keyValue[0];
                        String value;
                        if (keyValue.length > 1) {
                            value = keyValue[1];
                        } else {
                            value = null;
                        }
                        params.put(key, value);
                    }
                }
            }
        }
        return params;
    }
}
