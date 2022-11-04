package com.tsid.internal.sns;

import com.tsid.common.model.response.ApiResponse;
import com.tsid.internal.dto.req.EmailRequest;
import com.tsid.internal.dto.req.SnsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "SnsApiClient",
        url = "${internal.client.sns.base-url}",
        configuration = {
            SnsApiClientFeignConfig.class
        }
)
public interface SnsApiClient {

    @PostMapping("${internal.client.sns.send-email-url}")
    ApiResponse sendEmail(@RequestHeader("tsid-sns-auth") String auth,
                          @RequestBody EmailRequest.Email request);

    @PostMapping("${internal.client.sns.send-push-default}")
    ApiResponse push(@RequestHeader ("tsid-sns-auth") String auth,
                     @RequestBody SnsRequest.PushRegular request);

    @PostMapping("${internal.client.sns.send-push-all}")
    ApiResponse pushAll(@RequestHeader ("tsid-sns-auth") String auth,
                        @RequestBody SnsRequest.PushRegular request);

    @PostMapping("${internal.client.sns.send-sms-custom}")
    ApiResponse sendSmsCustom(@RequestHeader ("tsid-sns-auth") String auth,
                              @RequestBody SnsRequest.SmsCustom request);

    @PostMapping("${internal.client.sns.send-sms-default}")
    ApiResponse sendSms(@RequestHeader ("tsid-sns-auth") String auth,
                        @RequestBody SnsRequest.SmsRegular request);

}
