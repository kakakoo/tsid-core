package com.tsid.external.api.naver;

import com.tsid.external.api.naver.dto.req.NaverSmsRequest;
import com.tsid.external.api.naver.dto.res.NaverSmsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(
    name = "NaverSnsApiClient",
    url = "${external.client.naver.sns.base-url}",
    configuration = {
        NaverSnsFeignConfig.class
    }
)
public interface NaverSnsApiClient {

    @RequestMapping(method = RequestMethod.POST,
            value = "${external.client.naver.sns.url}",
            produces = "application/json")
    NaverSmsResponse sendSms(@RequestHeader("x-ncp-apigw-timestamp") String timestamp,
                             @RequestHeader("x-ncp-iam-access-key")  String accessKey,
                             @RequestHeader("x-ncp-apigw-signature-v2") String signature,
                             NaverSmsRequest.SmsBody smsBody);
}
