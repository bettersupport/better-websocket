package cn.better.websocket.core.exception;

/**
 * @author wang.wencheng
 * @date 2021-8-3
 * @remark
 */
public class WebSocketMapException extends RuntimeException{

    public WebSocketMapException() {
    }

    public WebSocketMapException(String message) {
        super(message);
    }

    public WebSocketMapException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebSocketMapException(Throwable cause) {
        super(cause);
    }

    public WebSocketMapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
