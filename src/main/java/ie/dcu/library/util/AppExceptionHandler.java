package ie.dcu.library.util;

import ie.dcu.library.model.Error;
import ie.dcu.library.model.ErrorCode;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static ie.dcu.library.model.ErrorCode.INVALID_USER_REQUEST;


/*
 * @author Sheba Kurien
 */
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {LibraryServiceException.class})
    public ResponseEntity handleLectureServiceException(LibraryServiceException ex) {
        return mapToError(ex.getMessage(), ex.getErrorCode());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(e -> e.getField() + " "+ e.getDefaultMessage() ).collect(Collectors.toList());
        StringJoiner joiner = new StringJoiner(" \n ", "", "");
        errors.forEach(joiner::add);
        return mapToError(joiner.toString(), INVALID_USER_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Error> handleNotificationServiceException(Exception ex) {
        return mapToError(ex.getMessage(), ErrorCode.UNEXPECTED_SERVER_ERROR);
    }

    public static ResponseEntity<Error> mapToError(String message, ErrorCode errorCode) {
        return new ResponseEntity<>( Error.builder()
                .code(errorCode.getCodeName())
                .message(message)
                .status(errorCode.getHttpStatus().value()).build(),
                errorCode.getHttpStatus());
    }

}