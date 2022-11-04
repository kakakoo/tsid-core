package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class InternalServerException extends ApiServerException {
    public InternalServerException(ErrorCode errorCode, ErrorAction action, String message) {
        super(errorCode, action, message);
    }

    public InternalServerException(String message) {
        super(ErrorCode.E500_INTERNAL_SERVER, ErrorAction.NONE, message);
    }
}
