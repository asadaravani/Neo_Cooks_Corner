package kg.beganov.CooksCorner.exception.handler;

import kg.beganov.CooksCorner.exception.*;
import kg.beganov.CooksCorner.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConfirmationTokenExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleConfirmationTokenExpiredException(ConfirmationTokenExpiredException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, "ConfirmationTokenExpiredException",e.getMessage());
    }

    @ExceptionHandler(InvalidDataProvidedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleInvalidDataProvidedException(InvalidDataProvidedException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, "InvalidDataProvidedException",e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleUserAlreadyExistException(UserAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.CONFLICT, "UserAlreadyExistException",e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, "UserNotFoundException",e.getMessage());
    }

    @ExceptionHandler(UserNotVerifiedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleUserNotVerifiedException(UserNotVerifiedException e) {
        return new ExceptionResponse(HttpStatus.FORBIDDEN, "UserNotVerifiedException",e.getMessage());
    }
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleIOException(IOException e) {
        return new ExceptionResponse(HttpStatus.CONFLICT, "IOException","CLOUDINARY: "+e.getMessage());
    }
    @ExceptionHandler(SearchingErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleIOException(SearchingErrorException e) {
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "SearchingErrorException",e.getMessage());
    }

}
