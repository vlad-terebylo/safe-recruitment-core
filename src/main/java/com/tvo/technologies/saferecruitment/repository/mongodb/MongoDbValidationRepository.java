package com.tvo.technologies.saferecruitment.repository.mongodb;

import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import com.tvo.technologies.saferecruitment.repository.ValidationRepository;

public class MongoDbValidationRepository implements ValidationRepository {
    @Override
    public boolean save(String userId, ValidationResponse validationResponse) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public long count(String userId) {
        return 0;
    }

    @Override
    public long countByVerdict(ValidationVerdict verdict) {
        return 0;
    }

    @Override
    public long countByVerdict(String userId, ValidationVerdict verdict) {
        return 0;
    }
}
