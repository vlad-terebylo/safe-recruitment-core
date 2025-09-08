package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.exception.UserNotSpecifiedException;
import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import com.tvo.technologies.saferecruitment.model.statistics.UserStatistics;
import com.tvo.technologies.saferecruitment.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public Statistics getStatistics() {
        log.info("Entering getStatistics()");
        return statisticsRepository.getStatistics();
    }

    public UserStatistics getUserStatistics(String userId) {
        log.info("Entering getUserStatistics()");
        if (Objects.isNull(userId)) {
            log.warn("Called with invalid id");
            throw new UserNotSpecifiedException("During fetching user statistics an exception occurred.\n" +
                    "User id is null");
        }

        log.info("Getting user statistics");
        return statisticsRepository.getUserStatistics(userId);
    }
}
