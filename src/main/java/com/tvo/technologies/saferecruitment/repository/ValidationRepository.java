package com.tvo.technologies.saferecruitment.repository;

import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;

public interface ValidationRepository {
    boolean save(String userId, ValidationResponse validationResponse);

    long count();

    long count(String userId);

    long countByVerdict(ValidationVerdict verdict);

    long countByVerdict(String userId, ValidationVerdict verdict);
}
