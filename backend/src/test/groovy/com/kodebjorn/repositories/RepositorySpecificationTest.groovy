package com.kodebjorn.repositories

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

import java.sql.ResultSet
import java.sql.Statement

class RepositorySpecificationTest extends RepositorySpecification {

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
