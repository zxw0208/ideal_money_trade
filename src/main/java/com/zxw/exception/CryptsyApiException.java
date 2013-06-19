package com.zxw.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-19
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
public class CryptsyApiException extends RuntimeException {

    public CryptsyApiException() {
        super();
    }

    public CryptsyApiException(String message) {
        super(message);
    }

    public CryptsyApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptsyApiException(Throwable cause) {
        super(cause);
    }

    protected CryptsyApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
