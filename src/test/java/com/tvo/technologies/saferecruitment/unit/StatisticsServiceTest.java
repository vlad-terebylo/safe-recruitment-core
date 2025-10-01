package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.exception.UserNotFoundException;
import com.tvo.technologies.saferecruitment.exception.InvalidUserIdException;
import com.tvo.technologies.saferecruitment.exception.ValidationResponsesLogicalException;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import com.tvo.technologies.saferecruitment.repository.StatisticsRepository;
import com.tvo.technologies.saferecruitment.service.StatisticsService;
import com.tvo.technologies.saferecruitment.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

    private static final String PRESENT_USER_ID = "1";
    private static final String NOT_PRESENT_USER_ID = "2";

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private ValidationService validationService;

    @Test
    void should_not_return_empty_statistics() {
        long countedValidationResponses = 0;
        long countedValidationResponsesByVerdict = 0;

        when(validationService.countValidationResponses()).thenReturn(countedValidationResponses);
        when(validationService.countValidationResponsesByVerdict(ValidationVerdict.SCAM)).thenReturn(countedValidationResponsesByVerdict);

        assertThrows(IllegalArgumentException.class, () -> statisticsService.getStatistics());
    }

    @Test
    void should_throw_an_exception_if_validation_response_by_verdict_number_is_bigger_than_total_validated_number() {
        long countedValidationResponses = 20;
        long countedValidationResponsesByVerdict = 25;

        when(validationService.countValidationResponses()).thenReturn(countedValidationResponses);
        when(validationService.countValidationResponsesByVerdict(ValidationVerdict.SCAM)).thenReturn(countedValidationResponsesByVerdict);

        assertThrows(ValidationResponsesLogicalException.class, () -> statisticsService.getStatistics());
    }

    @Test
    void should_throw_an_exception_if_validation_response_by_verdict_number_is_fewer_than_than_zero() {
        long countedValidationResponses = 20;
        long countedValidationResponsesByVerdict = -1;

        when(validationService.countValidationResponses()).thenReturn(countedValidationResponses);
        when(validationService.countValidationResponsesByVerdict(ValidationVerdict.SCAM)).thenReturn(countedValidationResponsesByVerdict);

        assertThrows(IllegalArgumentException.class, () -> statisticsService.getStatistics());
    }

    @Test
    void should_return_statistics() {
        long countedValidationResponses = 100;
        long countedValidationResponsesByVerdict = 73;

        when(validationService.countValidationResponses()).thenReturn(countedValidationResponses);
        when(validationService.countValidationResponsesByVerdict(ValidationVerdict.SCAM)).thenReturn(countedValidationResponsesByVerdict);

        float percentage = (float) (countedValidationResponsesByVerdict * 100) / countedValidationResponses;
        Statistics statistics = new Statistics(percentage, countedValidationResponses);

        Statistics gotStatistics = statisticsService.getStatistics();
        assertEquals(statistics, gotStatistics);
    }

    @Test
    void should_return_user_statistics() {
        long countedValidationResponses = 100;
        long countedValidationResponsesByVerdict = 73;

        when(validationService.countValidationResponses(PRESENT_USER_ID)).thenReturn(countedValidationResponses);
        when(validationService.countValidationResponsesByVerdict(PRESENT_USER_ID, ValidationVerdict.SCAM)).thenReturn(countedValidationResponsesByVerdict);

        float percentage = (float) (countedValidationResponsesByVerdict * 100) / countedValidationResponses;
        Statistics statistics = new Statistics(percentage, countedValidationResponses);

        Statistics gotStatistics = statisticsService.getUserStatistics(PRESENT_USER_ID);
        assertEquals(statistics, gotStatistics);
    }

    @Test
    void should_return_empty_user_statistics() {
        long countedValidationResponses = 0;
        long countedValidationResponsesByVerdict = 0;
 
        when(validationService.countValidationResponses(PRESENT_USER_ID)).thenReturn(countedValidationResponses);
        when(validationService.countValidationResponsesByVerdict(PRESENT_USER_ID, ValidationVerdict.SCAM)).thenReturn(countedValidationResponsesByVerdict);

        assertThrows(IllegalArgumentException.class, () -> statisticsService.getUserStatistics(PRESENT_USER_ID));
    }

    @Test
    void should_throw_an_exception_if_user_statistics_validation_response_by_verdict_number_is_fewer_than_than_zero() {
        long countedValidationResponses = 20;
        long countedValidationResponsesByVerdict = -1;

        when(validationService.countValidationResponses(PRESENT_USER_ID)).thenReturn(countedValidationResponses);
        when(validationService.countValidationResponsesByVerdict(PRESENT_USER_ID, ValidationVerdict.SCAM)).thenReturn(countedValidationResponsesByVerdict);

        assertThrows(IllegalArgumentException.class, () -> statisticsService.getUserStatistics(PRESENT_USER_ID));
    }

    @Test
    void should_throw_an_exception_if_user_statistics_validation_response_by_verdict_number_is_bigger_than_total_validated_number() {
        long countedValidationResponses = 20;
        long countedValidationResponsesByVerdict = 25;

        when(validationService.countValidationResponses(PRESENT_USER_ID)).thenReturn(countedValidationResponses);
        when(validationService.countValidationResponsesByVerdict(PRESENT_USER_ID, ValidationVerdict.SCAM)).thenReturn(countedValidationResponsesByVerdict);

        assertThrows(ValidationResponsesLogicalException.class, () -> statisticsService.getUserStatistics(PRESENT_USER_ID));
    }

    @Test
    void should_throw_exception_if_user_id_is_null() {
        assertThrows(InvalidUserIdException.class, () -> statisticsService.getUserStatistics(null));
    }

    @Test
    void should_throw_exception_if_user_id_is_not_present() {
        when(validationService.countValidationResponses(NOT_PRESENT_USER_ID)).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> statisticsService.getUserStatistics(NOT_PRESENT_USER_ID));
    }
}
