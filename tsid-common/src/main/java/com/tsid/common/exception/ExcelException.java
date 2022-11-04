package com.tsid.common.exception;

import com.tsid.common.model.enums.ErrorAction;
import com.tsid.common.model.enums.ErrorCode;

public class ExcelException extends ApiServerException {

	public ExcelException(ErrorCode errorCode, ErrorAction action, String message) {
        super(errorCode, action, message);
    }

    public ExcelException(String message) {
        super(ErrorCode.E500_EXCEL_EXCEPTION,  ErrorAction.NONE, message);
    }

    public ExcelException(String message, Throwable cause) {
        super(ErrorCode.E500_EXCEL_EXCEPTION,  ErrorAction.NONE, String.format("message: %s cause : %s", message, cause.getMessage()));
    }
}
