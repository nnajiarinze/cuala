package com.tinkona.cuala.config;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
public class DataSourceConfig {
    @Bean(name = "dataSource")
    public DataSource rawdataSource(@Value("${spring.datasource.driverClassName}") String datasourceDriverClassName, @Value("${spring.datasource.url}") String datasourceUrl, @Value("${spring.datasource.username}") String datasourceUsername, @Value("${spring.datasource.password}") String password, @Value("${spring.datasource.max.connection.pool.size}") int maxPoolSize) throws IOException {
        final HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(datasourceDriverClassName);
        ds.setJdbcUrl(datasourceUrl);
        ds.setUsername(datasourceUsername);
        ds.setPassword(password);
        ds.setMaximumPoolSize(maxPoolSize);


        return ds;
    }
}
