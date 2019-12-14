package com.ehem.kakaopay.web.controller.advice;

import com.ehem.kakaopay.model.guarantee.exception.InvalidFieldValueException;
import com.ehem.kakaopay.web.message.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GuaranteeControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidFieldValueException.class)
    public ResponseEntity<ApiResponse> handleArticleAuthenticationException(IllegalArgumentException e) {
        log.info("handleArticleAuthenticationException() >> {}", e.getCause());

        return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
