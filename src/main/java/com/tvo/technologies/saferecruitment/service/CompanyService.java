package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.model.company.Company;
import com.tvo.technologies.saferecruitment.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return null;
    }

    public Company getCompanyInfo(int id) {
        return null;
    }

    public boolean addNewCompany(Company company) {
        return false;
    }
}
