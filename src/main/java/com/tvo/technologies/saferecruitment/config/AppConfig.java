package com.tvo.technologies.saferecruitment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
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
    public CompanyRepository getCompanyRepository() {
        return new InMemoryCompanyRepository();
    }

    @Bean
    public StatisticsRepository getStatisticsRepository() {
        return new InMemoryStatisticsRepository();
    }

    @Bean
    public UserRepository getUserRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public VacancyRepository getVacancyRepository() {
        return new InMemoryVacancyRepository();
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
    public OpenAIClient getOpenAiClient(AiProperties properties) {
        return new OpenAIOkHttpClient.Builder().apiKey(properties.openaiKey()).build();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
