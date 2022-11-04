package com.tsid.internal.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PushRequest {

    @Builder
    @Getter
    @Setter
    public static class PushData {
        private String title;
        private String message;
        private String alarmFlag;
        private String targetFlag;
        private Long targetId;
    }
}
