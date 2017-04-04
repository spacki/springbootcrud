package com.ge.hc.emrad.xer.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
//@EntityScan(basePackages = {"com.ge.hc.emrad.xer.domain"})
@EnableJpaRepositories(basePackages = {"com.ge.hc.emrad.xer.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
