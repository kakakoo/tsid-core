package com.tsid.external.api.slack;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "SlackWebhookApiClient",
    url = "${external.client.slack.webhook.base-url}",
    configuration = {
        SlackWebhookApiFeignConfig.class
    }
)
public interface SlackWebhookApiClient {

    @PostMapping(value = "${slack.token.tsid.dev.notification}", produces = "application/json")
    void sendTsidDevNotificationMessage(@RequestBody JSONObject request);

    @PostMapping(value = "${slack.token.tsid.prod.notification}", produces = "application/json")
    void sendTsidProdNotificationMessage(@RequestBody JSONObject request);

    @PostMapping(value = "${slack.token.statistic.dev}", produces = "application/json")
    void sendTsidDevStatisticMessage(@RequestBody JSONObject request);

}
