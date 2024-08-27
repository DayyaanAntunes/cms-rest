package com.challenge.cms.configuration.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@RequiredArgsConstructor
@Getter
public class ErrorPlayload {
    private LocalDateTime timestamp = LocalDateTime.now();;
    private String status;
    private String path;
    private String trace;
}
