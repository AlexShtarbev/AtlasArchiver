package com.ashtarbev.atlas.archiver.ui.config;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "spring")
public class SpringClientConfig {
//    @Value("${baseUrl}")
    String baseUrl;
}
