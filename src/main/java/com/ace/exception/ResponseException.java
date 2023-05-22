package com.ace.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: CustomException
 * @Date: 9/11/2021 11:20 下午
 * @Author: garlam
 * @Description:
 */


public class ResponseException extends RuntimeException {
    private static final Logger log = LogManager.getLogger(ResponseException.class.getName());

    //异常错误编码 默认为0
    private int code;
    //异常信息
    private final String message;


    public ResponseException(ResponseExceptionType exceptionTypeEnum, String message) {
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ResponseException(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}

