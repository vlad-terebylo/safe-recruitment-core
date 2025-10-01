package com.tvo.technologies.saferecruitment.repository.inmemory;

import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import com.tvo.technologies.saferecruitment.repository.StatisticsRepository;

public class InMemoryStatisticsRepository implements StatisticsRepository {
    @Override
    public Statistics getStatistics() {
        return null;
    }

    @Override
    public Statistics getUserStatistics(String id) {
        return null;
    }
}
