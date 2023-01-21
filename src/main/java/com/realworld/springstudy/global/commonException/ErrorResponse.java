package com.realworld.springstudy.global.commonException;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@ToString
public class ErrorResponse {

    private Timestamp timestamp;
    private HttpStatus httpStatus;
    private int status;
    private String error;
    private String path;
    private String code;
    private String exception;

    @Builder
    public ErrorResponse(CommonCode commonCode, String error, String exception, String path) {
        this.timestamp = new Timestamp(new Date().getTime());
        this.httpStatus = commonCode.getStatus();
        this.status = commonCode.getStatus().value();
        this.error = (StringUtils.hasText(error) ? error : commonCode.getMessage());
        this.code = commonCode.getCode();
        this.exception = exception;
        this.path = path;
    }
}
