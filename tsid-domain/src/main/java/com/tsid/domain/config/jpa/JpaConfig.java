package com.tsid.domain.config.jpa;

import com.tsid.domain.TsidDomainRoot;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = {TsidDomainRoot.class})
@EnableJpaRepositories(basePackageClasses = {TsidDomainRoot.class})
@EnableJpaAuditing
public class JpaConfig {

}
