package com.tvo.technologies.saferecruitment.controller;

import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import com.tvo.technologies.saferecruitment.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/validation")
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping("/vacancy")
    public ResponseEntity<ValidationResponse> vacancyValidation(VacancyValidationRequest vacancy) {
        return ResponseEntity.ok(this.validationService.vacancyValidation(vacancy));
    }

    @PostMapping("/company")
    public ResponseEntity<ValidationResponse> companyValidation(CompanyValidationRequest company) {
        return ResponseEntity.ok(this.validationService.companyValidation(company));
    }
}
