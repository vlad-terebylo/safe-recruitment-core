package com.tvo.technologies.saferecruitment.config;

import com.tvo.technologies.saferecruitment.client.AiClient;
import com.tvo.technologies.saferecruitment.client.ChatGptAiClient;
import com.tvo.technologies.saferecruitment.repository.*;
import com.tvo.technologies.saferecruitment.repository.inmemory.*;
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
    public AiClient getAiClient() {
        return new ChatGptAiClient();
    }

    @Bean
    public ValidationRepository getValidationResult() {
        return new InMemoryValidationRepository();
    }
}
