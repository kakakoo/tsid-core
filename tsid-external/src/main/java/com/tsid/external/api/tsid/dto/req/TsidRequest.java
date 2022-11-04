package com.tsid.external.api.tsid.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TsidRequest {

    @Getter
    @Setter
    @Builder
    public static class TokenRequest {
        private String grant_type;
        private String client_id;
        private String client_secret;
        private String redirect_uri;
        private String code;

        public TokenRequest of(String grant_type, String client_id, String client_secret, String redirect_uri, String code){
            return TokenRequest
                    .builder()
                    .grant_type(grant_type)
                    .client_id(client_id)
                    .client_secret(client_secret)
                    .redirect_uri(redirect_uri)
                    .code(code)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class CertRequest {
        private String type;
        private String state;
        private String callback_url;
    }
}
