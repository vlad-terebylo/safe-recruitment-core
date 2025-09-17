package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.exception.InvalidUserIdException;
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
        log.info("Getting global statistics");
        return statisticsRepository.getStatistics();
    }

    public UserStatistics getUserStatistics(String userId) {
        if (Objects.isNull(userId)) {
            log.error("Getting statistics for user with nullable id");
            throw new InvalidUserIdException("During fetching user statistics an exception occurred.\n" +
                    "User id is null");
        }

        log.info("Getting statistics for user with id: {}", userId);
        return statisticsRepository.getUserStatistics(userId);
    }
}
