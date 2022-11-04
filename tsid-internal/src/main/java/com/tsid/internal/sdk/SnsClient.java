package com.tsid.internal.sdk;

import com.tsid.common.model.response.ApiResponse;
import com.tsid.internal.api.sns.SnsApiClient;
import com.tsid.internal.dto.req.SnsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SnsClient {

    @Value("${internal.client.sns.auth}")
    private String SNS_AUTH;

    private final SnsApiClient snsApiClient;

    public ApiResponse sendPush(String payload, List<Long> userIds){
        SnsRequest.PushRegular request = SnsRequest.PushRegular.builder()
                .payload(payload)
                .userIds(userIds)
                .build();
        return snsApiClient.push(SNS_AUTH, request);
    }

    public ApiResponse sendPushAll(SnsRequest.PushRegular request){
        return snsApiClient.pushAll(SNS_AUTH, request);
    }

    public ApiResponse sendSms(SnsRequest.SmsRegular request) {
        return snsApiClient.sendSms(SNS_AUTH, request);
    }

    public ApiResponse sendSmsCustom(SnsRequest.SmsCustom request) {
        return snsApiClient.sendSmsCustom(SNS_AUTH, request);
    }
}
