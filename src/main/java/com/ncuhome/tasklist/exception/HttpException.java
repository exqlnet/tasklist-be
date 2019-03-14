package com.ncuhome.tasklist.exception;

import lombok.Data;

import java.io.IOException;

@Data
public class HttpException extends IOException {

    private Integer httpCode;

    private String message;

    public HttpException(Integer httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    public HttpException(){}
}
