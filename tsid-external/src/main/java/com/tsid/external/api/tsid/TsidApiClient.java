package com.tsid.external.api.tsid;

import com.tsid.external.api.tsid.dto.req.TsidRequest;
import com.tsid.external.api.tsid.dto.req.TsidTokenRequest;
import com.tsid.external.api.tsid.dto.res.TsidResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
    name = "tsid-auth",
    url = "${external.client.tsid.base-url}",
    configuration = {
        TsidApiClientFeignConfig.class
    }
)
public interface TsidApiClient {

    @PostMapping("${external.client.tsid.token-url}")
    TsidResponse.TokenResponse getAccessToken(TsidTokenRequest request);

    @GetMapping("${external.client.tsid.profile-url}")
    TsidResponse.UserResponse getUserInfo(@RequestHeader("Authorization") String accessToken);

    @PostMapping("${external.client.tsid.cert-make-url}")
    TsidResponse.CertResponse makeCert(@RequestHeader("Authorization") String accessToken,
                                       TsidRequest.CertRequest request);

    @PostMapping("${external.client.tsid.cert-check-url}")
    TsidResponse.CertCheckResponse checkCert(@RequestHeader("Authorization") String accessToken,
                                             @RequestParam("type") String type,
                                             @RequestParam("state") String state);

}
