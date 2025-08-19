package com.tvo.technologies.saferecruitment.repository;

import com.tvo.technologies.saferecruitment.model.vacancy.Vacancy;

import java.util.List;

public interface VacancyRepository {
    List<Vacancy> getAllVacancies();

    Vacancy getVacancy(int id);

    boolean save(Vacancy vacancy);
}
