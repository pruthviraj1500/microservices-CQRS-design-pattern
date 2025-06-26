package com.programming.response;

import java.time.LocalDateTime;

public class ApiResponse {
    private LocalDateTime timestamp;

    private String message;

    public ApiResponse(String msg){
        super();
        this.message = msg;
        this.timestamp = LocalDateTime.now();
    }
}
