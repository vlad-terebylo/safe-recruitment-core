package com.tvo.technologies.saferecruitment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.tvo.technologies.saferecruitment.properties.AiProperties;
import com.tvo.technologies.saferecruitment.repository.*;
import com.tvo.technologies.saferecruitment.repository.inmemory.*;
import com.tvo.technologies.saferecruitment.service.AiValidationService;
import com.tvo.technologies.saferecruitment.service.GeminiAiValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserMetadataRepository getUserRepository() {
        return new InMemoryUserMetadataRepository();
    }

    @Bean
    public AiValidationService getAiAnalyzingService(Client client, ObjectMapper mapper) {
        return new GeminiAiValidationService(client, mapper);
    }

    @Bean
    public Client getGeminiClient(AiProperties properties) {
        return Client.builder()
                .apiKey(properties.geminiKey())
                .build();
    }

    @Bean
    public ValidationRepository getValidationResult() {
        return new InMemoryValidationRepository();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
