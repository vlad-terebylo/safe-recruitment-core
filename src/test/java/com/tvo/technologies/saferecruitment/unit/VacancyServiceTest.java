package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.repository.VacancyRepository;
import com.tvo.technologies.saferecruitment.service.VacancyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VacancyServiceTest {

    @Mock
    private VacancyRepository vacancyRepository;

    @InjectMocks
    private VacancyService vacancyService;

    @Test
    void should_get_all_vacancies(){

    }

    @Test
    void should_get_vacancy_list_if_it_is_empty(){

    }

    @Test
    void should_get_vacancy_by_id(){

    }

    @Test
    void should_throw_exception_if_vacancy_id_is_wrong(){

    }

    @Test
    void should_add_new_vacancy(){

    }

    @Test
    void should_not_add_new_vacancy_if_it_exists_in_database(){

    }
}
