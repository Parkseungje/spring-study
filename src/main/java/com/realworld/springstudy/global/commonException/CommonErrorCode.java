package com.realworld.springstudy.global.commonException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements CommonCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "000", "bad reqeust "),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "001", "unauthorized "),
    NOT_FOUND(HttpStatus.NOT_FOUND, "002", "not found "),
    CONFLICT(HttpStatus.CONFLICT, "003", "conflict "),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "004", "internal server error "),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "005", "bad gateway ");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
