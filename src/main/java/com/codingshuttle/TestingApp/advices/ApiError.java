package com.codingshuttle.TestingApp.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Represents an error response with an error code, message, and HTTP status.
 */
@Data
@Builder
public class ApiError {
    public String errorCode;
    private String message;
    private HttpStatus status;
}
