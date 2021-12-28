package com.ashtarbev.atlas.archiver.data;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJdbcRepositories
@ComponentScan(basePackages = { "com.ashtarbev.atlas.archiver" })
public class CockroachDbTestDataConfiguration {
}
