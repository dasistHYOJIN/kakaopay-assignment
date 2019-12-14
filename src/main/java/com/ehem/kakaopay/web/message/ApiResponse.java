package com.ehem.kakaopay.web.message;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse {
    private HttpStatus httpStatus;
    private String msg;
    private Object data;

    private ApiResponse() {
    }

    public ApiResponse(final HttpStatus httpStatus, final String msg) {
        this.httpStatus = httpStatus;
        this.msg = msg;
    }

    public ApiResponse(final HttpStatus httpStatus, final Object data) {
        this.httpStatus = httpStatus;
        this.data = data;
    }
}
