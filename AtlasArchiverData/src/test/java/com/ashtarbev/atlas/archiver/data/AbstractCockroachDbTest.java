package com.ashtarbev.atlas.archiver.data;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.CockroachContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

// Useful examples for:
// Flyway:
//  * https://gist.github.com/coderatchet/cfa73134bd4bbb9b3acfe0dab7fb82bb

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = { CockroachDbTestDataConfiguration.class, FlywayAutoConfiguration.class })
@Testcontainers
public abstract class AbstractCockroachDbTest {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    // Needs to be static so that it runs first before all the automatic
    // configurations are run.
    @ClassRule
    public static CockroachContainer cockroachDbContainer = new CockroachContainer("cockroachdb/cockroach:v21.2.3")
            .waitingFor(Wait.forHttp("/").forStatusCode(200))
            .withLogConsumer(outputFrame -> System.out.println("LOG: " + outputFrame.getUtf8String()));

    @BeforeClass
    public static void setUp() throws Exception {
        Flyway flyway = getFlyway();
        flyway.clean();
        flyway.baseline();
        flyway.migrate();
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> cockroachDbContainer.getJdbcUrl());
        registry.add("spring.datasource.username", () -> DB_USERNAME);
        registry.add("spring.datasource.password", () -> DB_PASSWORD);
    }

    static Flyway getFlyway() {
        ClassicConfiguration configuration = new ClassicConfiguration();
        configuration.setUrl(cockroachDbContainer.getJdbcUrl());
        configuration.setDataSource(cockroachDbContainer.getJdbcUrl(), DB_USERNAME, DB_PASSWORD);
        return new Flyway(configuration);
    }
}
