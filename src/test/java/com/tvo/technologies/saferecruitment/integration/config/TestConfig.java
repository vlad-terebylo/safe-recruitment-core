package com.tvo.technologies.saferecruitment.integration.config;

import com.google.genai.Client;
import com.google.genai.types.HttpOptions;
import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryUserRepository;
import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryValidationRepository;
import com.tvo.technologies.saferecruitment.repository.UserRepository;
import com.tvo.technologies.saferecruitment.repository.ValidationRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public UserRepository getTestUserRepository() {
        return new TestInMemoryUserRepository();
    }

    @Bean
    @Primary
    public ValidationRepository getTestValidationRepository() {
        return new TestInMemoryValidationRepository();
    }

    @Bean
    @Primary
    public Client getTestGeminiClient() {
        return Client.builder()
                .apiKey("test-key")
                .httpOptions(HttpOptions.builder()
                        .baseUrl("http://localhost:8080")
                        .apiVersion("v1beta")
                        .build())
                .build();
    }
}
