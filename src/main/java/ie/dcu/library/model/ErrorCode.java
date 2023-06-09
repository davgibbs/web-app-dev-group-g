package ie.dcu.library.model;
import org.springframework.http.HttpStatus;

/*
 * @author Sheba Kurien
 */
public enum ErrorCode {

    INVALID_USER_REQUEST(HttpStatus.BAD_REQUEST),
    UNEXPECTED_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    MAIL_SENDING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    MAIL_CLIENT_SENDING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    BOOK_NOT_FOUND(HttpStatus.BAD_REQUEST),
    ISBN_NOT_FOUND(HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST),
    BOOK_NOT_AVAIL(HttpStatus.BAD_REQUEST),

    USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED),

    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST),

    ROLE_NOT_FOUND(HttpStatus.BAD_REQUEST),
    USER_FORBIDDEN(HttpStatus.FORBIDDEN);
    private final HttpStatus status;
    ErrorCode(HttpStatus status) {
        this.status = status;
    }


    public HttpStatus getHttpStatus() {
        return this.status;
    }

    public String getCodeName() {
        return name();
    }
}
