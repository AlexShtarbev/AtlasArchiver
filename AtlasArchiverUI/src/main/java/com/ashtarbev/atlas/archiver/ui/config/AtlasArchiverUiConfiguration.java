package com.ashtarbev.atlas.archiver.ui.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.util.ObjectMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtlasArchiverUiConfiguration {
    @Bean
    public ObjectMapper atlasArchiverObjectMapper() {
        return new ObjectMapper();
    }
}
