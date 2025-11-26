package com.tvo.technologies.saferecruitment.model.validation;

import com.tvo.technologies.saferecruitment.model.enums.RiskCategory;
import com.tvo.technologies.saferecruitment.model.enums.RedFlags;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;

import java.util.List;

public record ValidationResponse(
        ValidationVerdict verdict,
        String description,
        RiskCategory riskCategory,
        List<RedFlags> redFlags) {
}
