package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.exception.InvalidUserIdException;
import com.tvo.technologies.saferecruitment.exception.ValidationResponsesLogicalException;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final ValidationService validationService;

    public Statistics getStatistics() {
        log.info("Getting global statistics");
        long countedValidationResponses = validationService.countValidationResponses();
        long countedValidationResponsesByVerdict = validationService.countValidationResponsesByVerdict(ValidationVerdict.SCAM);

        return buildStatistics(countedValidationResponses, countedValidationResponsesByVerdict);
    }

    public Statistics getUserStatistics(String userId) {
        log.info("Getting statistics for user with id: {}", userId);

        if (Objects.isNull(userId)) {
            log.error("Getting statistics for user with nullable id");
            throw new InvalidUserIdException("During fetching user statistics an exception occurred.\n" +
                    "User id is null");
        }

        long countedValidationResponses = validationService.countValidationResponses(userId);
        long countedValidationResponsesByVerdict = validationService.countValidationResponsesByVerdict(userId, ValidationVerdict.SCAM);

        return buildStatistics(countedValidationResponses, countedValidationResponsesByVerdict);
    }

    private Statistics buildStatistics(long countedValidationResponses, long countedValidationResponsesByVerdict) {
        if (countedValidationResponses == 0) {
            return new Statistics(0, 0);
        }

        if (countedValidationResponsesByVerdict > countedValidationResponses) {
            throw new ValidationResponsesLogicalException("The number of validated responses by verdict should not be greater than the total number of responses");
        }

        float percentageOfScamVacancies = (float) (countedValidationResponsesByVerdict * 100) / countedValidationResponses;

        return new Statistics(percentageOfScamVacancies, countedValidationResponses);
    }
}
