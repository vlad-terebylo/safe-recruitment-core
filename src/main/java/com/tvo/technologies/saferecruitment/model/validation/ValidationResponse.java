package com.tvo.technologies.saferecruitment.model.validation;

import com.tvo.technologies.saferecruitment.model.enums.RiskCategory;
import com.tvo.technologies.saferecruitment.model.enums.RedFlags;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;

public record ValidationResponse(
        ValidationVerdict verdict,
        String description,
        RiskCategory riskCategory,
        RedFlags redFlags) {
}
