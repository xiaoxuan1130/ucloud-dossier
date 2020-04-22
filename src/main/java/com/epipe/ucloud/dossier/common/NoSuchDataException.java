package com.epipe.ucloud.dossier.common;

/**
 * @author gongtao
 * @date 2019-04-22 10:25
 **/
public class NoSuchDataException extends BusiException {

    public NoSuchDataException() {
        super();
    }

    public NoSuchDataException(String message) {
        super(message);
    }

    public NoSuchDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDataException(Throwable cause) {
        super(cause);
    }

    protected NoSuchDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
