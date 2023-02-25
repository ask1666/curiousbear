package com.kodebjorn.repositories

import org.flywaydb.core.Flyway
import org.flywaydb.core.api.Location
import org.flywaydb.core.api.configuration.ClassicConfiguration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
abstract class RepositorySpecification extends Specification {

    private static final Logger logger = LoggerFactory.getLogger(this.class)

    @Shared
    final PostgreSQLContainer container = new PostgreSQLContainer('postgres:14-alpine')
            .withDatabaseName("test_db")
            .withUsername("postgres")
            .withPassword("postgres")

    /**
     * Migrate flyway on container database
     */
    def setup() {
        def config = new ClassicConfiguration()
        config.setDataSource(
                container.jdbcUrl,
                container.username,
                container.password
        )
        config.setLocations(new Location("classpath:db/migration"))

        def flyway = new Flyway(config)
        flyway.migrate()

        logger.info("Flyway migrations completed successfully.")
    }
}
