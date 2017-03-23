
package com.gomore.gomorelibrary.utils;


public class GomoreException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GomoreException() {
        super();
    }

    public GomoreException(String msg) {
        super(msg);
    }

    public GomoreException(Throwable ex) {
        super(ex);
    }

    public GomoreException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
