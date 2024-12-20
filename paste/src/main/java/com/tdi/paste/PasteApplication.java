package com.tdi.paste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PasteApplication {
    public static void main(String[] args) {
        SpringApplication.run(PasteApplication.class, args);
    }
}