package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class BadGatewayException extends ApiServerException {

    public BadGatewayException(String message) {
        super(ErrorCode.E502_BAD_GATEWAY, ErrorAction.NONE, message);
    }

    public BadGatewayException(ErrorCode errorCode, ErrorAction action, String message){
        super(errorCode, action, message);
    }
}
