package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class NotFoundException extends ApiServerException{

    public NotFoundException(ErrorCode errorCode, ErrorAction action, String message) {
        super(errorCode, action, message);
    }

    public NotFoundException(String message) {
        super(ErrorCode.E200_NOT_FOUND, ErrorAction.TOAST, message);
    }


}
