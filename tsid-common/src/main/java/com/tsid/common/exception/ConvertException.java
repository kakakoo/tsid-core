package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class ConvertException extends RuntimeException {

    private ErrorCode errorCode;

    private ErrorAction action;

    public ConvertException(ErrorCode errorCode, ErrorAction action, String message) {
        super(message);
        this.errorCode = errorCode;
        this.action = action;
    }

    public ConvertException(String message){
        super(message);
        this.errorCode = ErrorCode.E499_CONVERT_EXCEPTION;
        this.action = ErrorAction.NONE;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public ErrorAction getAction() { return action; }

}
