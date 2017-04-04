package com.ge.hc.emrad.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by karstenspakowski on 21/03/17.
 */

@Configuration
@EnableAutoConfiguration
//@EntityScan(basePackages = {"com.ge.hc.emrad.xer.domain"})
@EnableJpaRepositories(basePackages = {"com.ge.hc.emrad.xer.repository"})
@EnableTransactionManagement
public class RepositoryTestConfiguration {

}


