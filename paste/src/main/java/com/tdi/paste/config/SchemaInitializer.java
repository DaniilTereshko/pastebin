package com.tdi.paste.config;

import com.tdi.paste.config.properties.LiquibaseProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class SchemaInitializer implements BeanPostProcessor {
    private static final String CREATE_SCHEMA_SQL = "CREATE SCHEMA IF NOT EXISTS %s";
    private final LiquibaseProperties liquibaseProperties;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource ds) {
            try (
                    var con = ds.getConnection();
                    var statement = con.createStatement()
            ) {
                statement.execute(String.format(CREATE_SCHEMA_SQL, liquibaseProperties.getSchemaName()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return bean;
    }
}