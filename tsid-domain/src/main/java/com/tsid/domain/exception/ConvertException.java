package com.tsid.domain.exception;

public class ConvertException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ExceptionCode exceptionCode;

    public ConvertException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ConvertException(String message) {
        super(message);
    }

    public ExceptionCode getErrorCode() {
        return exceptionCode;
    }
}