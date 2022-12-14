package com.tsid.external.api.naver.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class NaverSmsRequest {

    @Builder
    @Getter
    public static class SmsBody{
        private String type;
        private String from;
        private String subject;
        private String content;
        private List<SmsMessage> messages;
    }

    @Getter
    @AllArgsConstructor
    public static class SmsMessage{
        private String to;
    }
}
