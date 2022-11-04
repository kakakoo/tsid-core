package com.tsid.external.feign;

import com.tsid.common.model.Constants;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;


@EnableFeignClients(basePackages = Constants.BASE_PACKAGE)
@Configuration
public class FeignClientConfig {

}
