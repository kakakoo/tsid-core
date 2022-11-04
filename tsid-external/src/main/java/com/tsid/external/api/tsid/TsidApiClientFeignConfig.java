package com.tsid.external.api.tsid;

import com.tsid.common.exception.BadGatewayException;
import com.tsid.common.exception.InvalidException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class TsidApiClientFeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new TsidApiClientErrorDecoder();
    }

    private static class TsidApiClientErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {
            FeignException exception = FeignException.errorStatus(methodKey, response);
            switch (response.status()) {
                case 400: case 401: case 403:
                    throw new InvalidException(String.format("TSID OAuth API 호출 중 잘못된 파라미터가 입력되었습니다. status: (%s) message: (%s)", response.status(), response.body()));
                case 500:
                    throw new InvalidException(String.format("TSID OAuth API 서버 오류 발생. status: (%s) message: (%s)", response.status(), response.body()));
                default:
                    throw new BadGatewayException(String.format("TSID OAuth API 호출중 에러(%s)가 발생하였습니다. message: (%s) ", response.status(), exception.getMessage()));
            }
        }

    }

}
