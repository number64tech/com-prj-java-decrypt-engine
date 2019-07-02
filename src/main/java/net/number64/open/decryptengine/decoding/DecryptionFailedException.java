package net.number64.open.decryptengine.decoding;

public class DecryptionFailedException extends Exception {
    public DecryptionFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DecryptionFailedException() {
    }

    public DecryptionFailedException(String message) {
        super(message);
    }

    public DecryptionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
