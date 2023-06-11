package com.quaz.ticket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class Config {

    @Bean
    public Clock clock() {
        return Clock.fixed(
                Instant.parse("2023-06-06T15:00:00.00Z"), ZoneId.of("Z")
        );
    }

}
