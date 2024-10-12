package com.ruoyi.common.exception;

public class TokenFailException extends RuntimeException{

    public TokenFailException(String message) {
        super(message);
    }

    public TokenFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
