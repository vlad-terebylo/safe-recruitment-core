package com.tvo.technologies.saferecruitment.model.vacancy;

import com.tvo.technologies.saferecruitment.model.RiskCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VacancyValidationResponse {
    private String conclusion;
    private RiskCategory riskCategory;
}
