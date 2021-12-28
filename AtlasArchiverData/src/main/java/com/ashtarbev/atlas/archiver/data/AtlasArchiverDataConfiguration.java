package com.ashtarbev.atlas.archiver.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.ashtarbev.atlas.archiver.data"})
@PropertySource({"classpath:application-data.yml"})
@ConfigurationProperties(prefix = "data")
public class AtlasArchiverDataConfiguration {

}
