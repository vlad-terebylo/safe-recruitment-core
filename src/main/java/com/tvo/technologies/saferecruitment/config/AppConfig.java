package com.tvo.technologies.saferecruitment.config;

import com.tvo.technologies.saferecruitment.client.AiClient;
import com.tvo.technologies.saferecruitment.client.ChatGptAiClient;
import com.tvo.technologies.saferecruitment.repository.CompanyRepository;
import com.tvo.technologies.saferecruitment.repository.StatisticsRepository;
import com.tvo.technologies.saferecruitment.repository.UserRepository;
import com.tvo.technologies.saferecruitment.repository.VacancyRepository;
import com.tvo.technologies.saferecruitment.repository.inmemory.InMemoryCompanyRepository;
import com.tvo.technologies.saferecruitment.repository.inmemory.InMemoryStatisticsRepository;
import com.tvo.technologies.saferecruitment.repository.inmemory.InMemoryUserRepository;
import com.tvo.technologies.saferecruitment.repository.inmemory.InMemoryVacancyRepository;
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
}
