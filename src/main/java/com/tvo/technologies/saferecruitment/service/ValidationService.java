package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.client.AiClient;
import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final AiClient aiClient;

    private final CompanyService companyService;
    private final VacancyService vacancyService;

    public ValidationResponse vacancyValidation(VacancyValidationRequest vacancy) {
        return null;
    }

    public ValidationResponse companyValidation(CompanyValidationRequest company) {
        return null;
    }
}
