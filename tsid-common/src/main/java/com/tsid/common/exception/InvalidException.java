package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class InvalidException extends ApiServerException {

    public InvalidException(ErrorCode errorCode, ErrorAction action, String message) {
        super(errorCode, action, message);
    }

    public InvalidException(String message) {
        super(ErrorCode.E400_INVALID_EXCEPTION, ErrorAction.NONE, message);
    }

}
