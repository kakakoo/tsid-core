package com.tsid.common.model.response;

import com.tsid.common.model.enums.ErrorAction;
import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private String code;
    private ErrorAction type;
    private String message;
}