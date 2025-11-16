package com.tvo.technologies.saferecruitment.integration.config.repository;

import com.tvo.technologies.saferecruitment.model.company.Company;

import java.util.List;

public class TestCompanyRepository {
    public boolean save(Company company) {
        System.out.println("Save in test company repository");
        return false;
    }

    public Company getCompanyById(int id) {
        return null;
    }

    public List<Company> getAllCompanies() {
        return null;
    }

    public void clear(){

    }
}
