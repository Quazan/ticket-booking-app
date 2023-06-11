package com.quaz.ticket.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public static Clock fixedClock() {
        return Clock.fixed(
                Instant.parse("2023-01-01T15:00:00.00Z"), ZoneId.of("Z")
        );
    }

}