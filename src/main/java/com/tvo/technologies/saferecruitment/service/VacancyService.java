package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.exception.VacancyIsNotValidException;
import com.tvo.technologies.saferecruitment.exception.VacancyNotFoundException;
import com.tvo.technologies.saferecruitment.model.vacancy.Vacancy;
import com.tvo.technologies.saferecruitment.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public List<Vacancy> getAllVacancies() {
        log.info("Getting the list of vacancies");
        return vacancyRepository.getAllVacancies();
    }

    public Vacancy getVacancy(int id) {
        if (id < 0) {
            log.error("Getting information about vacancy with wrong id");
            throw new VacancyNotFoundException("Id must not be less than zero");
        }

        log.info("Getting vacancy with id {}", id);
        return vacancyRepository.getVacancy(id);
    }

    public boolean addNewVacancy(Vacancy vacancy) {
        if (isInvalid(vacancy)) {
            log.error("Trying to add invalid vacancy. Some parameters are null");
            throw new VacancyIsNotValidException("Some of vacancy parameters are null");
        }

        log.info("New company was successfully added");
        return vacancyRepository.save(vacancy);
    }

    private boolean isInvalid(Vacancy vacancy) {
        return Objects.isNull(vacancy.getPosition())
                || Objects.isNull(vacancy.getDescription())
                || Objects.isNull(vacancy.getRequiredSkills());
    }
}
