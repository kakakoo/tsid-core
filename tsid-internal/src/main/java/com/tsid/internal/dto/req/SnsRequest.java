package com.tsid.internal.dto.req;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class SnsRequest {

    @Builder
    @Getter
    public static class PushRegular {
        @NotNull(message = "사용자 아이디는 필수값입니다.")
        private List<Long> userIds;
        @NotNull(message = "푸쉬 데이터는 필수값입니다.")
        private String payload;
    }

    @Builder
    @Getter
    public static class SmsRegular {
        @Pattern(regexp = "(^$|[0-9]{11})", message = "올바른 휴대폰번호 형식이 아닙니다.")
        @NotNull(message = "사용자 전화번호는 필수값입니다.")
        private String userTel;
        @NotNull(message = "SMS 타입은 필수값입니다.")
        private String smsType;
    }

    @Getter
    @Builder
    public static class SmsCustom {
        @Pattern(regexp = "(^$|[0-9]{11})", message = "올바른 휴대폰번호 형식이 아닙니다.")
        @NotNull(message = "사용자 전화번호는 필수값입니다.")
        private String userTel;
        @NotNull(message = "메세지 타입은 필수값입니다.")
        private String messageType;
        @NotNull(message = "메세지 제목은 필수값입니다.")
        private String subject;
        @NotNull(message = "메세지 내용은 필수값입니다.")
        private String content;
    }
}
