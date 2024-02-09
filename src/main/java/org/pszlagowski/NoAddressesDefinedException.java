package org.pszlagowski;

public class NoAddressesDefinedException extends Exception {

    public NoAddressesDefinedException() {
    }

    public NoAddressesDefinedException(String message) {
        super(message);
    }

    public NoAddressesDefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAddressesDefinedException(Throwable cause) {
        super(cause);
    }

    public NoAddressesDefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
