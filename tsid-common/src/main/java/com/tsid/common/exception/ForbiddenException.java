package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class ForbiddenException extends ApiServerException {

    public ForbiddenException(ErrorCode errorCode, ErrorAction action, String message) {
        super(errorCode, action, message);
    }

    public ForbiddenException(String message) {
        super(ErrorCode.E403_FORBIDDEN_EXCEPTION, ErrorAction.NONE, message);
    }

}
