package com.tsid.external.api.naver.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@AllArgsConstructor
public class NaverSmsResponse {
    private String requestId;
    private String statusCode;
    private String statusName;
    private Timestamp requestTime;
}
