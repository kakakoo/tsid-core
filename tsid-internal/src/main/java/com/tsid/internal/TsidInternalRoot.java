package com.tsid.internal;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackageClasses = {
    TsidInternalRoot.class,
})
@Configuration
public interface TsidInternalRoot {
}
