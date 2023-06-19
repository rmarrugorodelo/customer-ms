package com.rmarrugo.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;

    public ErrorResponse(Integer status,
                         String message,
                         String path
    ) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }

}
