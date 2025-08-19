package com.tvo.technologies.saferecruitment.repository;

import com.tvo.technologies.saferecruitment.model.company.Company;

import java.util.List;

public interface CompanyRepository {
    boolean save(Company company);

    Company getCompanyById(int id);

    List<Company> getAllCompanies();
}
