package com.tsid.external;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackageClasses = {
    TsidExternalRoot.class,
})
@Configuration
public interface TsidExternalRoot {
}
