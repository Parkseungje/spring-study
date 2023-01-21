package com.realworld.springstudy.global.commonException;

import org.springframework.http.HttpStatus;

public interface CommonCode {

    String getCode();
    HttpStatus getStatus();
    String getMessage();

}
