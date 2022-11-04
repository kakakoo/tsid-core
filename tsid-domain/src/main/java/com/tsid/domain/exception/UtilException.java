package com.tsid.domain.exception;

public class UtilException extends RuntimeException {

    private static final long serialVersionUID = 100L;

    private ExceptionCode exceptionCode;

    public UtilException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public UtilException(String message) {
        super(message);
    }

    public ExceptionCode getErrorCode() {
        return exceptionCode;
    }
}