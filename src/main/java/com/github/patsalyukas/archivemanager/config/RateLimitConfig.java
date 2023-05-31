package com.github.patsalyukas.archivemanager.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfig {

    @Value("${archivemanager.ratelimit}")
    private int ratelimit;

    @Bean
    public Bucket createBucket() {
        return  Bucket.builder()
                .addLimit(Bandwidth.classic(ratelimit, Refill.intervally(ratelimit, Duration.ofMinutes(1))))
                .build();
    }
}
