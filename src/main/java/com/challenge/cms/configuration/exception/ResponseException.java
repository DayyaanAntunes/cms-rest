package com.challenge.cms.configuration.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ResponseException extends RuntimeException {
    private final String message;
    private final HttpStatus status;

}