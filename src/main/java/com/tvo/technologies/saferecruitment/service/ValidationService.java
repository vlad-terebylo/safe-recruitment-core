package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.client.AiClient;
import com.tvo.technologies.saferecruitment.exception.InvalidCompanyValidationRequestException;
import com.tvo.technologies.saferecruitment.exception.InvalidVacancyRequestException;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import com.tvo.technologies.saferecruitment.repository.ValidationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final AiClient aiClient;
    private final ValidationRepository validationRepository;

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
        if (Objects.isNull(vacancy)) {
            throw new InvalidVacancyRequestException("The vacancy validation request must not be null");
        }

        if(checkVacancyValidationRequestFieldsIfItIsNull(vacancy)){
            throw new InvalidVacancyRequestException("Some of fields in vacancy validation request are null");
        }

        return aiClient.validate(vacancy);
    }

    private boolean checkVacancyValidationRequestFieldsIfItIsNull(VacancyValidationRequest vacancy) {
        return Objects.isNull(vacancy.position())
                || Objects.isNull(vacancy.description())
                || Objects.isNull(vacancy.requiredSkills())
                || Objects.isNull(vacancy.salary());
    }

    public ValidationResponse companyValidation(CompanyValidationRequest company) {
        if(Objects.isNull(company)){
            throw new InvalidCompanyValidationRequestException("The company validation request is null");
        }

        if(checkCompanyValidationRequestFieldsIfItIsNull(company)){
            throw new InvalidCompanyValidationRequestException("Some of fields in vacancy validation request are null");
        }

        return aiClient.validate(company);
    }

    private boolean checkCompanyValidationRequestFieldsIfItIsNull(CompanyValidationRequest company) {
        return Objects.isNull(company.title())
                || Objects.isNull(company.address())
                || Objects.isNull(company.website())
                || Objects.isNull(company.reviews());
    }
}
