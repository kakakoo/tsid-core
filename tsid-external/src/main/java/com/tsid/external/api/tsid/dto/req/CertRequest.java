package com.tsid.external.api.tsid.dto.req;

import lombok.Getter;

public class CertRequest {


    @Getter
    public static class MakeRequest {
        private String certRole;
    }

    @Getter
    public static class CheckRequest {
        private String state;
    }

    @Getter
    public static class CallbackRequest {
        private String platform;
        private String state;
        private String code;
        private String type;
    }
}
