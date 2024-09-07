package com.codingshuttle.TestingApp.advices;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private String timestamp;  // Use String to hold formatted timestamp
    private T data;
    private ApiError error;

    // Define the date-time pattern for Indian Standard Time (IST)
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Constructor to initialize the timestamp with Indian Standard Time (IST) and format it
    public ApiResponse(T data) {
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).format(FORMATTER);
        this.data = data;
    }

    // Constructor to initialize the response with an error and timestamp, formatted
    public ApiResponse(ApiError error) {
        this.timestamp = ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).format(FORMATTER);
        this.error = error;
    }
}
