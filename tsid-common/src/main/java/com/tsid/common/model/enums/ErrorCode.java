package com.tsid.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /*
     * 200 Success (status: false)
     */
    E200_SUCCESS(200, "S2000", "성공"),
    E200_LOGIN(200, "S2001", ""),
    E200_CREATE_COMPANY(200, "S2002", ""),
    E200_UPDATE_COMPANY(200, "S2003", ""),
    E200_DELETE_COMPANY(200, "S2004", ""),
    E200_VIEW_COMPANY_USER(200, "S2005", ""),
    E200_VIEW_COMPANY_DETAIL(200, "S2006", ""),

    E200_NOT_FOUND(200, "S2007", "해당하는 데이터가 존재하지 않습니다."),

    /**
     * 400 Bad Request (잘못된 요청)
     */
    E400_INVALID_EXCEPTION(400, "I4000", "파라미터 오류"),
    E400_INVALID_AUTH_TOKEN(400, "I4005", "유효하지 않는 토큰입니다."),
    E400_INVALID_FILE_FORMAT_EXCEPTION(400 , "I4006", "잘못된 형식의 파일입니다."),
    E400_INVALID_IMAGE_SIZE(400 , "I4007", "지원되지 않는 이미지 사이즈입니다."),

    /**
     * 401 UnAuthorized (토큰 만료)
     */
    E401_UNAUTHORIZED(401, "U4100", "엑세스 토큰 만료"),
    E401_UNAUTHORIZED_EXCEL(401, "U4100", "엑셀 엑세스 토큰 만료"),

    /**
     * 403 Forbidden (권한이 없을 경우)
     */
    E403_FORBIDDEN_EXCEPTION( 403, "F4300", "해당 권한이 없습니다"),

    /**
     * 405 Method Not Allowed
     */
    E405_METHOD_NOT_ALLOWED_EXCEPTION(405, "M4500", "허용되지 않는 HTTP 메소드입니다."),

    /**
     * 409 Conflict
     */
    E409_CONFLICT_EXCEPTION(409, "C4090", "클라이언트의 요청과 서버가 충돌합니다."),

    /**
     * 415 Unsupported media type (지원하지 않는 미디어 타입)
     */
    E415_UNSUPPORTED_MEDIA_TYPE(415, "U4500", "지원하지 않는 미디어 타입입니다."),

    /**
     *  470 Internal Rule (내부 예외 규칙 강제 로그 아웃)
     */
    E470_INTERNAL_RULE(470, "R4700", "세션이 만료되어 강제 로그아웃 됩니다."),
    E470_INTERNAL_RULE_LOGOUT(470, "R4701", "세션이 만료되어 강제 로그아웃 됩니다."),
    E470_INTERNAL_RULE_REFRESH(470, "R4702", "세션이 만료되어 강제 로그아웃 됩니다."),

    /**
     * 499 Convert (데이터 변환 오류)
     */
    E499_CONVERT_EXCEPTION(499, "X4900", "컨버트 오류"),


    /**
     * 500 Internal Server Exception (서버 내부 에러)
     */
    E500_INTERNAL_SERVER(500, "Z5000", "예상치 못한 에러가 발생하였습니다. 다시 시도해 주세요."),
    E500_INTERNAL_SERVER_ENCRYPT(500, "Z5001", "암호화 오류"),
    E500_INTERNAL_SERVER_DECRYPT(500, "Z5002","복호화 오류"),
    E500_EXCEL_EXCEPTION(500, "Z5003", "excel"),
    /**
     * 502 Bad Gateway
     */
    E502_BAD_GATEWAY(502,"B5200", "일시적인 오류가 발생했습니다. 다시 시도해 주세요.");

    private final int status;
    private final String code;
    private final String message;

    public int getStatus() {return this.status; }
    public String getCode() { return this.code; }
    public String getMessage() { return this.message; }
}
