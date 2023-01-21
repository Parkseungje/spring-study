package com.realworld.springstudy.global.exception;

import com.realworld.springstudy.global.commonException.BaseException;
import com.realworld.springstudy.global.commonException.CommonErrorCode;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(CommonErrorCode commonCode) {
        super(commonCode);
    }

    public UserNotFoundException(CommonErrorCode commonCode, String message) {
        super(commonCode, message);
    }
}
