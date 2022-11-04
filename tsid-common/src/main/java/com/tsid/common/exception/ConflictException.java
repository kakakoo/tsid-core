package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class ConflictException extends ApiServerException{

    public ConflictException(ErrorCode errorCode, ErrorAction action, String message) {
        super(errorCode, action, message);
    }

    public ConflictException(String message) {
        super(ErrorCode.E409_CONFLICT_EXCEPTION, ErrorAction.TOAST, message);
    }
}
