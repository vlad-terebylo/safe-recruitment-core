package com.tvo.technologies.saferecruitment.controller;

import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import com.tvo.technologies.saferecruitment.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/validation")
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping("/vacancy/{userId}")
    public ResponseEntity<ValidationResponse> vacancyValidation(@RequestParam String userId, @RequestBody VacancyValidationRequest vacancy) {
        return ResponseEntity.ok(this.validationService.vacancyValidation(userId, vacancy));
    }

    @PostMapping("/company/{userId}")
    public ResponseEntity<ValidationResponse> companyValidation(@RequestParam String userId, @RequestBody CompanyValidationRequest company) {
        return ResponseEntity.ok(this.validationService.companyValidation(userId, company));
    }
}
