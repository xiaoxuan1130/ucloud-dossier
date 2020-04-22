package com.epipe.ucloud.dossier.common;

/**
 * 业务异常
 * @author gongtao
 * @date 2019-03-18 14:45
 **/
public class BusiException extends RuntimeException {

    public BusiException() {
        super();
    }

    public BusiException(String message) {
        super(message);
    }

    public BusiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusiException(Throwable cause) {
        super(cause);
    }

    protected BusiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
