package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    public ValidationResponse vacancyValidation(VacancyValidationRequest vacancy) {
        return null;
    }

    public ValidationResponse companyValidation(CompanyValidationRequest company) {
        return null;
    }
}
