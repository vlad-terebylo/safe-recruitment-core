package com.tvo.technologies.saferecruitment.integration.config.repository;

import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;

public class TestValidationRepository {
    public boolean save(String userId, ValidationResponse validationResponse) {
        return false;
    }

    public long count() {
        return 0;
    }

    public long count(String userId) {
        return 0;
    }

    public long countByVerdict(ValidationVerdict verdict) {
        return 0;
    }

    public long countByVerdict(String userId, ValidationVerdict verdict) {
        return 0;
    }
}
