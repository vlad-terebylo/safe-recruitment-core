package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.exception.VacancyAlreadyExistsException;
import com.tvo.technologies.saferecruitment.exception.VacancyIsNotValidException;
import com.tvo.technologies.saferecruitment.exception.VacancyNotFoundException;
import com.tvo.technologies.saferecruitment.model.vacancy.Vacancy;
import com.tvo.technologies.saferecruitment.repository.VacancyRepository;
import com.tvo.technologies.saferecruitment.service.VacancyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VacancyServiceTest {

    private static final int PRESENT_VACANCY_ID = 1;
    private static final int NOT_PRESENT_VACANCY_ID = -1;

    @Mock
    private VacancyRepository vacancyRepository;

    @InjectMocks
    private VacancyService vacancyService;

    @Test
    void should_get_all_vacancies() {
        List<Vacancy> expectedVacancies = List.of(new Vacancy(
                "Java Developer",
                "Empty",
                List.of("Spring Boot",
                        "Java",
                        "PostgreSQL",
                        "Docker")
        ));
        when(vacancyRepository.getAllVacancies()).thenReturn(expectedVacancies);

        List<Vacancy> vacancies = vacancyService.getAllVacancies();

        assertEquals(expectedVacancies, vacancies);
    }

    @Test
    void should_get_vacancy_list_if_it_is_empty() {
        when(vacancyRepository.getAllVacancies()).thenReturn(List.of());

        assertEquals(List.of(), vacancyService.getAllVacancies());
    }

    @Test
    void should_get_vacancy_by_id() {
        Vacancy expectedVacancy = new Vacancy(
                "Java Developer",
                "Empty",
                List.of("Spring Boot",
                        "Java",
                        "PostgreSQL",
                        "Docker"));

        when(vacancyRepository.getVacancy(PRESENT_VACANCY_ID)).thenReturn(expectedVacancy);

        Vacancy vacancy = vacancyService.getVacancy(PRESENT_VACANCY_ID);

        assertEquals(expectedVacancy, vacancy);
    }

    @Test
    void should_throw_exception_if_vacancy_id_is_wrong() {
        assertThrows(VacancyNotFoundException.class, () -> vacancyService.getVacancy(NOT_PRESENT_VACANCY_ID));
    }

    @Test
    void should_add_new_vacancy() {
        Vacancy vacancy = new Vacancy(
                "Java Developer",
                "Empty",
                List.of("Spring Boot",
                        "Java",
                        "PostgreSQL",
                        "Docker"));

        when(vacancyRepository.save(vacancy)).thenReturn(true);

        boolean isAdded = vacancyService.addNewVacancy(vacancy);

        assertTrue(isAdded);

        verify(vacancyRepository, times(1)).save(vacancy);
    }

    @Test
    void should_not_add_new_vacancy_if_it_exists_in_database() {
        Vacancy vacancy = new Vacancy(
                "Java Developer",
                "Empty",
                List.of("Spring Boot",
                        "Java",
                        "PostgreSQL",
                        "Docker"));

        when(vacancyRepository.save(vacancy)).thenThrow(VacancyAlreadyExistsException.class);

        assertThrows(VacancyAlreadyExistsException.class, () -> vacancyService.addNewVacancy(vacancy));
    }

    @Test
    void should_throw_vacancy_is_not_valid_exception() {
        Vacancy invalidVacancy = new Vacancy("Java software developer", "No description", null);
        assertThrows(VacancyIsNotValidException.class, () -> vacancyService.addNewVacancy(invalidVacancy));
    }
}
