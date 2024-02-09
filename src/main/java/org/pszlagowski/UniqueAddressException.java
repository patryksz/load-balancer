package org.pszlagowski;

public class UniqueAddressException extends Exception {

    public UniqueAddressException() {
    }

    public UniqueAddressException(String message) {
        super(message);
    }

    public UniqueAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueAddressException(Throwable cause) {
        super(cause);
    }

    public UniqueAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
