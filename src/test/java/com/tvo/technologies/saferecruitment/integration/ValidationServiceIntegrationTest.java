package com.tvo.technologies.saferecruitment.integration;

import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryValidationRepository;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.service.ValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ValidationServiceIntegrationTest extends AbstractServiceTest {
    private static final String TEST_USER_ID = "1";

    @Autowired
    private TestInMemoryValidationRepository validationRepository;

    @Autowired
    private ValidationService validationService;

    @AfterEach
    public void cleanUp() {
        validationRepository.clear();
    }

    @Test
    void should_get_valid_scam_vacancy_validation_response() {
        stubFor(post(urlPathMatching("/v1beta/models/gemini-2.5-flash:generateContent"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("Text.")));
    }

    @Test
    void should_get_valid_truthfulness_vacancy_validation_response() {

    }

    @Test
    void should_not_get_valid_vacancy_validation_response_if_validation_request_is_invalid() {

    }

    @Test
    void should_throw_exception_if_some_vacancy_validation_request_fields_are_null() {

    }

    @Test
    void should_get_valid_company_validation_response() {

    }

    @Test
    void should_not_get_valid_company_validation_response_if_validation_request_is_invalid() {

    }

    @Test
    void should_throw_exception_if_some_company_validation_request_fields_are_null() {

    }

    @ParameterizedTest
    @ValueSource(longs = {0, 12})
    void should_count_number_of_global_validation_responses(long expected) {

    }

    @ParameterizedTest
    @ValueSource(longs = {0, 7})
    void should_count_number_of_validation_responses_for_certain_user(long expected) {

    }

    @ParameterizedTest(name = "Verdict = {0}")
    @EnumSource(ValidationVerdict.class)
    void should_count_number_of_global_validation_responses_by_verdict(ValidationVerdict verdict) {

    }

    @ParameterizedTest(name = "Verdict = {0}")
    @EnumSource(ValidationVerdict.class)
    void should_count_number_of_validation_responses_for_certain_user_by_truthful_verdict(ValidationVerdict verdict) {

    }
}
