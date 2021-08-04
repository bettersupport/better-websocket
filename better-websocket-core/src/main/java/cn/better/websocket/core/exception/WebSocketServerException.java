package cn.better.websocket.core.exception;

/**
 * @author wang.wencheng
 * @date 2021-8-4
 * @remark
 */
public class WebSocketServerException extends RuntimeException{

    public WebSocketServerException() {
    }

    public WebSocketServerException(String message) {
        super(message);
    }

    public WebSocketServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebSocketServerException(Throwable cause) {
        super(cause);
    }

    public WebSocketServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
