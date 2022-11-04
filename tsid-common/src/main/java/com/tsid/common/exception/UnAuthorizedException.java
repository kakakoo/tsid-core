package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class UnAuthorizedException extends ApiServerException {

    public UnAuthorizedException(ErrorCode errorCode, ErrorAction action, String message) {
        super(errorCode, action, message);
    }

    public UnAuthorizedException(String message) {
        super(ErrorCode.E401_UNAUTHORIZED, ErrorAction.NONE, message);
    }

}
