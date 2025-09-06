package com.tvo.technologies.saferecruitment.repository.mongodb;

import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import com.tvo.technologies.saferecruitment.model.statistics.UserStatistics;
import com.tvo.technologies.saferecruitment.repository.StatisticsRepository;

public class MongoDbStatisticsRepository implements StatisticsRepository {
    @Override
    public Statistics getStatistics() {
        return null;
    }

    @Override
    public UserStatistics getUserStatistics(String id) {
        return null;
    }
}
