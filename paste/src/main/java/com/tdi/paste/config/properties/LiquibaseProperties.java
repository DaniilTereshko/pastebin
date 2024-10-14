package com.tdi.paste.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties
@EnableConfigurationProperties
public class LiquibaseProperties {

    @Value("${spring.liquibase.default-schema}")
    private String schemaName;
}