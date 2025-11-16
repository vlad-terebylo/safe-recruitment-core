package com.tvo.technologies.saferecruitment.integration;

import com.tvo.technologies.saferecruitment.integration.config.repository.TestValidationRepository;
import com.tvo.technologies.saferecruitment.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;

public class StatisticsServiceIntegrationTest extends AbstractServiceTest {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private TestValidationRepository validationRepository;
}
