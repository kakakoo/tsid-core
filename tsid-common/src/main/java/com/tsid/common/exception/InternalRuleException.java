package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class InternalRuleException extends RuntimeException {

    private ErrorCode errorCode;

    private ErrorAction action;

    public InternalRuleException(ErrorCode errorCode, ErrorAction action, String message) {
        super(message);
        this.errorCode = errorCode;
        this.action = action;
    }

    public InternalRuleException(){
        super(ErrorCode.E470_INTERNAL_RULE.getMessage());
        this.errorCode = ErrorCode.E470_INTERNAL_RULE;
        this.action = ErrorAction.TOAST;
    }

    public InternalRuleException(String message){
        super(message);
        this.errorCode = ErrorCode.E470_INTERNAL_RULE;
        this.action = ErrorAction.TOAST;
    }

    public InternalRuleException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.action = ErrorAction.TOAST;
    }


    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public ErrorAction getAction() { return action; }

}
