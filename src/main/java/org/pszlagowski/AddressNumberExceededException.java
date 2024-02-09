package org.pszlagowski;

public class AddressNumberExceededException extends Exception {
    public AddressNumberExceededException() {
        super();
    }

    public AddressNumberExceededException(String message) {
        super(message);
    }

    public AddressNumberExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNumberExceededException(Throwable cause) {
        super(cause);
    }

    protected AddressNumberExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
