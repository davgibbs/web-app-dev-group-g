package ie.dcu.library.util;
import ie.dcu.library.model.ErrorCode;

public class LibraryServiceException extends RuntimeException  {
    ErrorCode errorCode;
    public LibraryServiceException(String message, ErrorCode errorCode) {
        this(message, errorCode, null);
    }
    public LibraryServiceException(final String message, ErrorCode errorCode, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}