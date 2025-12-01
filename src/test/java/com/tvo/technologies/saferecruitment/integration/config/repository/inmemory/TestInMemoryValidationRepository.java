package com.tvo.technologies.saferecruitment.integration.config.repository.inmemory;

import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.validation.ValidationRecord;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class TestInMemoryValidationRepository {
    private final List<ValidationRecord> validationRecords = new ArrayList<>();

    public boolean save(String userId, ValidationResponse validationResponse) {
        ValidationRecord record = new ValidationRecord(userId, validationResponse);

        return validationRecords.add(record);
    }

    public long count() {
        return validationRecords.size();
    }

    public long count(String userId) {
        int id = Integer.parseInt(userId);

        List<ValidationRecord> sortedList = validationRecords.stream()
                .filter(record -> record.getId() == id)
                .toList();

        return sortedList.size();
    }

    public long countByVerdict(ValidationVerdict verdict) {
        List<ValidationRecord> sortedList = validationRecords.stream()
                .filter(record -> record.getVerdict().equals(verdict))
                .toList();

        return sortedList.size();
    }

    public long countByVerdict(String userId, ValidationVerdict verdict) {
        int id = Integer.parseInt(userId);

        List<ValidationRecord> sortedList = validationRecords.stream()
                .filter(record -> record.getVerdict().equals(verdict))
                .filter(record -> record.getId() == id)
                .toList();

        return sortedList.size();
    }

    public void clear() {
        validationRecords.clear();
    }
}
