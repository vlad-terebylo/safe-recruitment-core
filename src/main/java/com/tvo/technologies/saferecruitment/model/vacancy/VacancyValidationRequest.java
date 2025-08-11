package com.tvo.technologies.saferecruitment.model.vacancy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VacancyValidationRequest {
    private Vacancy vacancy;
}
