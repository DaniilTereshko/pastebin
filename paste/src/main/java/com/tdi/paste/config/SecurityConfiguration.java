package com.tdi.paste.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class SecurityConfiguration {

    @Bean
    public AuditorAware<Integer> auditorProvider() {
        return () -> Optional.of(1); //TODO заменить при реализации SecurityContext'а
    }
}