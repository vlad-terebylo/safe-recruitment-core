package com.tvo.technologies.saferecruitment.model.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserStatistics {
    private float percentageOfScamVacancies;
    private long totalNumberOfValidatedVacancies;
}
