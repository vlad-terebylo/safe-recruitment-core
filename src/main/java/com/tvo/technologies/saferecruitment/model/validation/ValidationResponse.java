package com.tvo.technologies.saferecruitment.model.validation;

import com.tvo.technologies.saferecruitment.model.enums.RiskCategory;

import java.util.List;

public record ValidationResponse(
        String conclusion,
        RiskCategory riskCategory,
        List<String> redFlags) {
}
