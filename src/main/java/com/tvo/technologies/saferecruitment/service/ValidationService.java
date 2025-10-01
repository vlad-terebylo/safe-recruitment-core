package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.client.AiClient;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import com.tvo.technologies.saferecruitment.repository.ValidationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final AiClient aiClient;
    private ValidationRepository validationRepository;

    public long countValidationResponses() {
        return validationRepository.count();
    }

    public long countValidationResponses(String userId) {
        return validationRepository.count(userId);
    }

    public long countValidationResponsesByVerdict(ValidationVerdict verdict) {
        return validationRepository.countByVerdict(verdict);
    }

    public long countValidationResponsesByVerdict(String userId, ValidationVerdict verdict) {
        return validationRepository.countByVerdict(userId, verdict);
    }

    public ValidationResponse vacancyValidation(VacancyValidationRequest vacancy) {
        return aiClient.validate("");
    }

    public ValidationResponse companyValidation(CompanyValidationRequest company) {
        return aiClient.validate("");
    }
}
