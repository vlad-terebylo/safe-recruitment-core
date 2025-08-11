package com.tvo.technologies.saferecruitment.model.company;

import com.tvo.technologies.saferecruitment.model.RiskCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyValidationResponse {
    private String conclusion;
    private RiskCategory riskCategory;
}
