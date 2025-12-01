package com.tvo.technologies.saferecruitment.integration;

import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryValidationRepository;
import com.tvo.technologies.saferecruitment.service.StatisticsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StatisticsServiceIntegrationTest extends AbstractServiceTest {

    private static final String PRESENT_USER_ID = "1";
    private static final String NOT_PRESENT_USER_ID = "-1";

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private TestInMemoryValidationRepository validationRepository;

    @AfterEach
    public void cleanUp() {
        validationRepository.clear();
    }

    @Test
    void should_not_return_empty_statistics() {

    }

    @Test
    void should_throw_an_exception_if_validation_response_by_verdict_number_is_bigger_than_total_validated_number() {

    }

    @Test
    void should_throw_an_exception_if_validation_response_by_verdict_number_is_fewer_than_than_zero() {

    }

    @Test
    void should_return_statistics() {

    }

    @Test
    void should_return_user_statistics() {

    }

    @Test
    void should_return_empty_user_statistics() {

    }

    @Test
    void should_throw_an_exception_if_user_statistics_validation_response_by_verdict_number_is_fewer_than_than_zero() {

    }

    @Test
    void should_throw_an_exception_if_user_statistics_validation_response_by_verdict_number_is_bigger_than_total_validated_number() {

    }

    @Test
    void should_throw_exception_if_user_id_is_null() {

    }

    @Test
    void should_throw_exception_if_user_id_is_not_present() {

    }
}
