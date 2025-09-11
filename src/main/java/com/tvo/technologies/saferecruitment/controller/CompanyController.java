package com.tvo.technologies.saferecruitment.controller;

import com.tvo.technologies.saferecruitment.model.company.Company;
import com.tvo.technologies.saferecruitment.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(this.companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        return ResponseEntity.ok(this.companyService.getCompanyById(id));
    }
}
