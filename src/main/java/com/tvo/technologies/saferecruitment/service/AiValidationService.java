package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;

public interface AiValidationService {
    ValidationResponse validate(VacancyValidationRequest vacancy);
    ValidationResponse validate(CompanyValidationRequest company);
}