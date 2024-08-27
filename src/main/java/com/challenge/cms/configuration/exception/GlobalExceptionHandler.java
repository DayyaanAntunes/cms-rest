package com.challenge.cms.configuration.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice(basePackages = "com.challenge.cms")
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorPlayload> handleGenericException(Exception exception, HttpServletRequest request) {
        ErrorPlayload error = new ErrorPlayload();
        error.setStatus(messageSource.getMessage("error.internal",null, Locale.getDefault()));
        error.setTrace(getFormatTrace(exception));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ResponseBody
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorPlayload> handleCustomException(ResponseException exception, HttpServletRequest request) {
        String message = messageSource.getMessage(exception.getMessage(), null, Locale.getDefault());
        ErrorPlayload playload = new ErrorPlayload();
        playload.setPath(getRequestPath(request));
        playload.setStatus(message);
        playload.setTrace(getFormatTrace(exception));
        return ResponseEntity.status(exception.getStatus()).body(playload);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorPlayload> handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        StringBuilder message = new StringBuilder();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            message.append(fieldError.getDefaultMessage()).append("-&-");
        }
        ErrorPlayload playload = new ErrorPlayload();
        playload.setPath(getRequestPath(request));
        playload.setStatus(message.toString());
        playload.setTrace("-");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(playload);
    }

    private String getFormatTrace(Exception exception){
        StackTraceElement element = exception.getStackTrace()[0];
        return String.format("%s: %s", element.getClassName(), element.getMethodName());
    }

    private String getRequestPath(HttpServletRequest request) {
        return request.getRequestURI();
    }


}