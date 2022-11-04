package com.tsid.external.api.tsid.dto.req;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TsidTokenRequest {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String code;

    public static TsidTokenRequest of(String grant_type, String client_id, String client_secret, String redirect_uri, String code){
        return TsidTokenRequest
                .builder()
                .grant_type(grant_type)
                .client_id(client_id)
                .client_secret(client_secret)
                .redirect_uri(redirect_uri)
                .code(code)
                .build();
    }
}
