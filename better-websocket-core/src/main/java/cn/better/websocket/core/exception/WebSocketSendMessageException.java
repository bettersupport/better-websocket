package cn.better.websocket.core.exception;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
public class WebSocketSendMessageException extends RuntimeException{

    public WebSocketSendMessageException() {
    }

    public WebSocketSendMessageException(String message) {
        super(message);
    }

    public WebSocketSendMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebSocketSendMessageException(Throwable cause) {
        super(cause);
    }

    public WebSocketSendMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
