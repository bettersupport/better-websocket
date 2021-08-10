package io.github.bettersupport.websocket.core.model;

/**
 * @author wang.wencheng
 * date 2021-8-5
 * describe
 */
public class WebSocketResponse<T> {

    private int code;

    private T result;

    private String error;

    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ResponseCode {

        public static final int SUCCESS = 200;

        public static final int NOT_FOUND = 404;

        public static final int SERVICE_ERROR = 500;

        public static final int REQUEST_ERROR = 501;

    }

    public static <T> WebSocketResponse<T> buildResult(T result) {
        return buildResult(result, ResponseCode.SUCCESS);
    }

    public static <T> WebSocketResponse<T> buildResult(T result, int responseCode) {
        WebSocketResponse<T> response = new WebSocketResponse();
        response.setResult(result);
        response.setCode(responseCode);
        response.setSuccess(true);
        return response;
    }

    public static <T> WebSocketResponse<T> buildError(String error) {
        return buildError(error, ResponseCode.REQUEST_ERROR);
    }

    public static <T> WebSocketResponse<T> buildError(String error, int responseCode) {
        WebSocketResponse<T> response = new WebSocketResponse();
        response.setError(error);
        response.setCode(responseCode);
        response.setSuccess(false);
        return response;
    }
}
