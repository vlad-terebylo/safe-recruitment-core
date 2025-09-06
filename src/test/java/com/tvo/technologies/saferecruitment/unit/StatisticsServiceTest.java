package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.exception.UserNotFoundException;
import com.tvo.technologies.saferecruitment.exception.UserNotSpecifiedException;
import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import com.tvo.technologies.saferecruitment.model.statistics.UserStatistics;
import com.tvo.technologies.saferecruitment.repository.StatisticsRepository;
import com.tvo.technologies.saferecruitment.service.StatisticsService;
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
    private StatisticsRepository statisticsRepository;

    @Test
    void should_return_empty_statistics() {
        Statistics emptyStatistics = new Statistics(0, 0);

        when(statisticsRepository.getStatistics()).thenReturn(emptyStatistics);

        Statistics gotStatistics = statisticsService.getStatistics();
        assertEquals(emptyStatistics, gotStatistics);
    }

    @Test
    void should_return_statistics() {
        Statistics statistics = new Statistics(73, 100);

        when(statisticsRepository.getStatistics()).thenReturn(statistics);

        Statistics gotStatistics = statisticsService.getStatistics();
        assertEquals(statistics, gotStatistics);
    }

    @Test
    void should_return_user_statistics() {
        UserStatistics userStatistics = new UserStatistics(73, 100);

        when(statisticsRepository.getUserStatistics(PRESENT_USER_ID)).thenReturn(userStatistics);

        UserStatistics gotStatistics = statisticsService.getUserStatistics(PRESENT_USER_ID);
        assertEquals(userStatistics, gotStatistics);
    }

    @Test
    void should_return_empty_user_statistics() {
        UserStatistics userStatistics = new UserStatistics(0, 0);

        when(statisticsRepository.getUserStatistics(PRESENT_USER_ID)).thenReturn(userStatistics);

        UserStatistics gotStatistics = statisticsService.getUserStatistics(PRESENT_USER_ID);
        assertEquals(userStatistics, gotStatistics);
    }

    @Test
    void should_throw_exception_if_user_id_is_null() {
        assertThrows(UserNotSpecifiedException.class, () -> statisticsService.getUserStatistics(null));
    }

    @Test
    void should_throw_exception_if_user_id_is_not_present() {
        when(statisticsRepository.getUserStatistics(NOT_PRESENT_USER_ID)).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> statisticsService.getUserStatistics(NOT_PRESENT_USER_ID));
    }
}
