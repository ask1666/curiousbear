package com.kodebjorn.repositories

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

import java.sql.ResultSet
import java.sql.Statement

@Testcontainers
abstract class RepositorySpecification extends Specification {

    @Shared
    PostgreSQLContainer container = new PostgreSQLContainer('postgres:9.6-alpine')
            .withDatabaseName("test_db")
            .withUsername("postgres")
            .withPassword("postgres")

    def "waits until postgres accepts jdbc connections"() {

        given: "a jdbc connection"
            HikariConfig hikariConfig = new HikariConfig()
            hikariConfig.setJdbcUrl(container.jdbcUrl)
            hikariConfig.setUsername("postgres")
            hikariConfig.setPassword("postgres")
            HikariDataSource ds = new HikariDataSource(hikariConfig)

        when: "querying the database"
            Statement statement = ds.getConnection().createStatement()
            statement.execute("SELECT 1")
            ResultSet resultSet = statement.getResultSet()
            resultSet.next()

        then: "result is returned"
            int resultSetInt = resultSet.getInt(1)
            resultSetInt == 1

        cleanup:
            ds.close()
    }
}
