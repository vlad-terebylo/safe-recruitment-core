package com.tvo.technologies.saferecruitment.integration.config;

import com.tvo.technologies.saferecruitment.integration.config.repository.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public TestStatisticsRepository getStatisticsRepo() {
        return new TestStatisticsRepository();
    }

    @Bean
    public TestUserRepository getUserRepo() {
        return new TestUserRepository();
    }

    @Bean
    public TestValidationRepository getValidationRes() {
        return new TestValidationRepository();
    }
}
