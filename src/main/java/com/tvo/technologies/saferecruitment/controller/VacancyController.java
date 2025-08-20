package com.tvo.technologies.saferecruitment.controller;

import com.tvo.technologies.saferecruitment.model.dto.BooleanResponseDto;
import com.tvo.technologies.saferecruitment.model.vacancy.Vacancy;
import com.tvo.technologies.saferecruitment.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<List<Vacancy>> getAllVacancies(){
        return ResponseEntity.ok(this.vacancyService.getAllVacancies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getVacancy(@PathVariable int id){
        return ResponseEntity.ok(this.vacancyService.getVacancy(id));
    }

    @PostMapping
    public ResponseEntity<BooleanResponseDto> addNewVacancy(@RequestBody Vacancy vacancy){
        return ResponseEntity.ok(new BooleanResponseDto(this.vacancyService.addNewVacancy(vacancy)));
    }
}
