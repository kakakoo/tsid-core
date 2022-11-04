package com.tsid.external.api.tsid.dto.res;

import lombok.Getter;

public class TsidResponse {

    @Getter
    public static class TokenResponse {
        private String access_token;
        private Long access_token_expires_in;
        private String refresh_token;
        private Long refresh_token_expires_in;
        private String grant_type;
    }

    @Getter
    public static class UserResponse {
        private String target_id;
        private String name;
        private String tel;
        private String email;
    }

    @Getter
    public static class CertResponse {
        private Boolean is_make;
        private Boolean is_group;
    }

    @Getter
    public static class CertCheckResponse {
        private String cert_date;
        private Boolean is_cert;
    }
}
