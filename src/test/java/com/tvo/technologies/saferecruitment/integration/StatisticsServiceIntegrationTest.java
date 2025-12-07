package com.tvo.technologies.saferecruitment.integration;

import com.tvo.technologies.saferecruitment.exception.InvalidUserIdException;
import com.tvo.technologies.saferecruitment.exception.UserNotFoundException;
import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryValidationRepository;
import com.tvo.technologies.saferecruitment.model.enums.RiskCategory;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import com.tvo.technologies.saferecruitment.model.validation.ValidationRecord;
import com.tvo.technologies.saferecruitment.service.StatisticsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatisticsServiceIntegrationTest extends AbstractServiceTest {

    private static final String PRESENT_USER_ID = "1";
    private static final String NOT_PRESENT_USER_ID = "-1";

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private TestInMemoryValidationRepository validationRepository;

    @AfterEach
    public void cleanUp() {
        validationRepository.clear();
    }

    @Test
    void should_not_return_empty_statistics() {
        Statistics emptyStatistics = statisticsService.getStatistics();

        assertEquals(0, emptyStatistics.getTotalNumberOfValidatedVacancies());
        assertEquals(0, emptyStatistics.getPercentageOfScamVacancies());
    }

    @Test
    void should_return_statistics() {
        ValidationRecord firstRecord = new ValidationRecord(
                1,
                "aswerfdghn",
                ValidationVerdict.SCAM,
                "description",
                RiskCategory.HIGH,
                List.of());

        ValidationRecord secondRecord = new ValidationRecord(
                2,
                "gfgrtred",
                ValidationVerdict.TRUTHFULNESS,
                "description",
                RiskCategory.LOW,
                List.of());

        validationRepository.save(firstRecord.getUserId(), firstRecord.toValidationResponse());
        validationRepository.save(secondRecord.getUserId(), secondRecord.toValidationResponse());

        Statistics statistics = statisticsService.getStatistics();
        assertEquals(2, statistics.getTotalNumberOfValidatedVacancies());
        assertEquals(50, statistics.getPercentageOfScamVacancies());
    }

    @Test
    void should_return_user_statistics() {
        ValidationRecord firstRecord = new ValidationRecord(
                1,
                "asdfdfg",
                ValidationVerdict.SCAM,
                "description",
                RiskCategory.HIGH,
                List.of());

        validationRepository.save(firstRecord.getUserId(), firstRecord.toValidationResponse());

        Statistics statistics = statisticsService.getUserStatistics(firstRecord.getUserId());
        assertEquals(1, statistics.getTotalNumberOfValidatedVacancies());
        assertEquals(100, statistics.getPercentageOfScamVacancies());
    }

    @Test
    void should_return_empty_user_statistics() {
        Statistics emptyStatistics = statisticsService.getUserStatistics(PRESENT_USER_ID);

        assertEquals(0, emptyStatistics.getTotalNumberOfValidatedVacancies());
        assertEquals(0, emptyStatistics.getPercentageOfScamVacancies());
    }

    @Test
    void should_throw_exception_if_user_id_is_null() {
        assertThrows(InvalidUserIdException.class, () -> statisticsService.getUserStatistics(null));
    }

    @Test
    void should_throw_exception_if_user_id_is_not_present() {
        ValidationRecord firstRecord = new ValidationRecord(
                1,
                "asdfg",
                ValidationVerdict.SCAM,
                "description",
                RiskCategory.HIGH,
                List.of());

        Statistics statistics = statisticsService.getUserStatistics(firstRecord.getUserId());
        assertEquals(0, statistics.getTotalNumberOfValidatedVacancies());
        assertEquals(0, statistics.getPercentageOfScamVacancies());
    }
}
