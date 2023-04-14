package pl.ioad.adoto.backend.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.ioad.adoto.backend.exception.handler.response.ErrorResponse;
import pl.ioad.adoto.communication.geoportal.exception.GeoportalTimeoutException;
import pl.ioad.adoto.communication.geoportal.exception.ResponseFailedException;
import pl.ioad.adoto.communication.geoportal.exception.WrongInputDataException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        final Map<String, String> errors = new HashMap<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }

        return handleExceptionInternal(
                ex,
                new ErrorResponse("Invalid data provided", HttpStatus.BAD_REQUEST.value(), errors),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler({GeoportalTimeoutException.class})
    public ResponseEntity<ErrorResponse> handleGeoportalAPITimeoutException(RuntimeException exc) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                .body(new ErrorResponse("Geoportal API timeout",
                        HttpStatus.REQUEST_TIMEOUT.value(), Map.of(exc.getClass().getSimpleName(), exc.getMessage())));
    }

    @ExceptionHandler({WrongInputDataException.class, ResponseFailedException.class})
    public ResponseEntity<ErrorResponse> handleWrongInputDataException(Exception exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exc.getMessage(),
                        HttpStatus.BAD_REQUEST.value(), Map.of(exc.getClass().getSimpleName(), exc.getMessage())));
    }
}
