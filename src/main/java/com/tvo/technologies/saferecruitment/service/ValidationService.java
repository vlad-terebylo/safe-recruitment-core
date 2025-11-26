package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.exception.InvalidCompanyValidationRequestException;
import com.tvo.technologies.saferecruitment.exception.InvalidVacancyRequestException;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import com.tvo.technologies.saferecruitment.repository.ValidationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationService {

    private final AiValidationService aiClient;
    private final ValidationRepository validationRepository;

    public long countValidationResponses() {
        log.info("Counting the total number of analyzed vacancies");

        return validationRepository.count();
    }

    public long countValidationResponses(String userId) {
        log.info("Counting the number of analyzed vacancies by user with id {}", userId);

        return validationRepository.count(userId);
    }

    public long countValidationResponsesByVerdict(ValidationVerdict verdict) {
        log.info("Counting the number of analyzed vacancies with certain verdict - {}", verdict);

        return validationRepository.countByVerdict(verdict);
    }

    public long countValidationResponsesByVerdict(String userId, ValidationVerdict verdict) {
        log.info("Counting the number of analyzed vacancies with certain verdict by user with id {} and verdict - {}", userId, verdict);

        return validationRepository.countByVerdict(userId, verdict);
    }

    public ValidationResponse vacancyValidation(String userId, VacancyValidationRequest vacancy) {
        if (Objects.isNull(vacancy)) {
            log.error("Validation request is null");
            throw new InvalidVacancyRequestException("The vacancy validation request must not be null");
        }

        if (checkVacancyValidationRequestFieldsIfItIsNull(vacancy)) {
            log.error("Some of fields of validation request are null");
            throw new InvalidVacancyRequestException("Some of fields in vacancy validation request are null");
        }

        log.info("Sending vacancy validation request for position {} to AI client", vacancy.position());

        ValidationResponse response = aiClient.validate(vacancy);
        validationRepository.save(userId, response);
        return response;
    }

    private boolean checkVacancyValidationRequestFieldsIfItIsNull(VacancyValidationRequest vacancy) {
        return Objects.isNull(vacancy.position())
                || Objects.isNull(vacancy.description())
                || Objects.isNull(vacancy.requiredSkills())
                || Objects.isNull(vacancy.salary());
    }

    public ValidationResponse companyValidation(String userId, CompanyValidationRequest company) {
        if (Objects.isNull(company)) {
            log.error("Validation request is null");
            throw new InvalidCompanyValidationRequestException("The company validation request is null");
        }

        if (checkCompanyValidationRequestFieldsIfItIsNull(company)) {
            log.error("Some of fields of validation request are null");
            throw new InvalidCompanyValidationRequestException("Some of fields in vacancy validation request are null");
        }

        log.info("Sending company with title {} validation request to AI client", company.title());

        ValidationResponse response = aiClient.validate(company);
        validationRepository.save(userId, response);
        return response;
    }

    private boolean checkCompanyValidationRequestFieldsIfItIsNull(CompanyValidationRequest company) {
        return Objects.isNull(company.title())
                || Objects.isNull(company.address())
                || Objects.isNull(company.website())
                || Objects.isNull(company.reviews());
    }
}
