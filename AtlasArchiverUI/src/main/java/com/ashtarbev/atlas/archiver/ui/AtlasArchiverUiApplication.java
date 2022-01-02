package com.ashtarbev.atlas.archiver.ui;


import com.ashtarbev.atlas.archiver.ui.config.SpringClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties({ SpringClientConfig.class })
public class AtlasArchiverUiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AtlasArchiverUiApplication.class, args);
    }

}
