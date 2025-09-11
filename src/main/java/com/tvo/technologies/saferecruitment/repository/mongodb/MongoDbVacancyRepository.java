package com.tvo.technologies.saferecruitment.repository.mongodb;

import com.tvo.technologies.saferecruitment.model.vacancy.Vacancy;
import com.tvo.technologies.saferecruitment.repository.VacancyRepository;

import java.util.List;

public class MongoDbVacancyRepository implements VacancyRepository {
    @Override
    public List<Vacancy> getAllVacancies() {
        return null;
    }

    @Override
    public Vacancy getVacancy(int id) {
        return null;
    }

    @Override
    public boolean save(Vacancy vacancy) {
        return false;
    }

    @Override
    public boolean equal(Vacancy vacancy) {
        return false;
    }
}
