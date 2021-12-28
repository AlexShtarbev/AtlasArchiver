package com.ashtarbev.atlas.archiver.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import com.ashtarbev.atlas.archiver.data.AtlasArchiverDataConfiguration;

@SpringBootApplication
@EnableJdbcRepositories(basePackages = {"com.ashtarbev.atlas.archiver.data"})
@ComponentScan(basePackages = "com.ashtarbev.atlas.archiver")
@Import(AtlasArchiverDataConfiguration.class)
public class AtlasArchiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlasArchiverApplication.class, args);
	}

}
