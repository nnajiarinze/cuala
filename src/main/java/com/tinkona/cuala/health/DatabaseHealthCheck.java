package com.tinkona.cuala.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by Nnaji.Arinze on 10/27/2016.
 */
@Component
public class DatabaseHealthCheck extends AbstractHealthIndicator {
    private final DataSource dataSource;

    @Autowired
    public DatabaseHealthCheck(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (this.dataSource.getConnection().isClosed()) {
            builder.down();
        } else {
            builder.up();
        }
    }
}