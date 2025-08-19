package com.tvo.technologies.saferecruitment.repository;

import com.tvo.technologies.saferecruitment.model.statistics.Statistics;

public interface StatisticsRepository {

    Statistics getStatistics();

    Statistics getUserStatistics(String id);
}
