package com.tvo.technologies.saferecruitment.model.validation;

import java.math.BigDecimal;
import java.util.List;

public record VacancyValidationRequest(
        String userId,
        String position,
        String description,
        List<String> requiredSkills,
        BigDecimal salary,
        String location,
        boolean interviewProcessDetails) {
}
