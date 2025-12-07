package com.tvo.technologies.saferecruitment.model.validation;

import com.tvo.technologies.saferecruitment.model.enums.RedFlags;
import com.tvo.technologies.saferecruitment.model.enums.RiskCategory;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationRecord {
    private int id;
    private String userId;
    private ValidationVerdict verdict;
    private String description;
    private RiskCategory riskCategory;
    private List<RedFlags> redFlags;

    public ValidationRecord(String userId, ValidationResponse response) {
        this.userId = userId;
        this.verdict = response.verdict();
        this.description = response.description();
        this.riskCategory = response.riskCategory();
        this.redFlags = response.redFlags();
    }

    public ValidationResponse toValidationResponse() {
        return new ValidationResponse(
                this.verdict,
                this.description,
                this.riskCategory,
                this.redFlags);
    }
}
