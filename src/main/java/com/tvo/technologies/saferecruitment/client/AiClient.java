package com.tvo.technologies.saferecruitment.client;

import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;

public interface AiClient {
    ValidationResponse validate(VacancyValidationRequest vacancy);
    ValidationResponse validate(CompanyValidationRequest company);
}