package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.exception.UserNotSpecifiedException;
import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import com.tvo.technologies.saferecruitment.model.statistics.UserStatistics;
import com.tvo.technologies.saferecruitment.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public Statistics getStatistics() {
        return statisticsRepository.getStatistics();
    }

    public UserStatistics getUserStatistics(String userId) {
        if (Objects.isNull(userId)) {
            throw new UserNotSpecifiedException("During fetching user statistics an exception occurred.\n" +
                    "User id is null");
        }

        return statisticsRepository.getUserStatistics(userId);
    }
}
