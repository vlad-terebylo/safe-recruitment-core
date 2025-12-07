package com.tvo.technologies.saferecruitment.integration.config;

import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryUserRepository;
import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryValidationRepository;
import com.tvo.technologies.saferecruitment.repository.UserRepository;
import com.tvo.technologies.saferecruitment.repository.ValidationRepository;
import com.tvo.technologies.saferecruitment.repository.inmemory.InMemoryUserRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public UserRepository getUserRepo() {
        return new TestInMemoryUserRepository();
    }

    @Bean
    @Primary
    public ValidationRepository getValidationRes() {
        return new TestInMemoryValidationRepository();
    }
}
