package com.realworld.springstudy.global.commonException;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Order
@RestControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler {

    @ExceptionHandler({BaseException.class})
    protected ResponseEntity<ErrorResponse> handleBaseException(BaseException e, HttpServletRequest request) {

        return this.createErrorResponse(e, request, e.getCommonCode());
    }

    protected ResponseEntity<ErrorResponse> createErrorResponse(Throwable e, HttpServletRequest request, CommonCode commonCode) {

        ErrorResponse body = ErrorResponse.builder()
                .commonCode(commonCode)
                .error((StringUtils.hasText(e.getMessage()) ? e.getMessage() : null))
                .exception(e.getClass().getSimpleName()).build();
        return new ResponseEntity<>(body, body.getHttpStatus());
    }
}
