package com.tvo.technologies.saferecruitment.repository;

import com.tvo.technologies.saferecruitment.model.statistics.Statistics;
import com.tvo.technologies.saferecruitment.model.statistics.UserStatistics;

public interface StatisticsRepository {

    Statistics getStatistics();

    UserStatistics getUserStatistics(String id);
}
