package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.client.AiClient;
import com.tvo.technologies.saferecruitment.service.CompanyService;
import com.tvo.technologies.saferecruitment.service.VacancyService;
import com.tvo.technologies.saferecruitment.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {
    @Mock
    private AiClient aiClient;
    @Mock
    private CompanyService companyService;
    @Mock
    private VacancyService vacancyService;

    @InjectMocks
    private ValidationService validationService;

    @Test
    void should_get_valid_vacancy_validation_response() {

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

}
