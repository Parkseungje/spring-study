package com.realworld.springstudy.global.commonException;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final CommonErrorCode commonCode;


    public BaseException(CommonErrorCode commonCode) {
        this.commonCode = commonCode;
    }

    public BaseException(CommonErrorCode commonCode, String message) {
        super(message);
        this.commonCode = commonCode;
    }
}
