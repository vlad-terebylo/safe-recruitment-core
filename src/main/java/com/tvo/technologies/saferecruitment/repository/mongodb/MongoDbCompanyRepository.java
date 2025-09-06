package com.tvo.technologies.saferecruitment.repository.mongodb;

import com.tvo.technologies.saferecruitment.model.company.Company;
import com.tvo.technologies.saferecruitment.repository.CompanyRepository;

import java.util.List;

public class MongoDbCompanyRepository implements CompanyRepository {
    @Override
    public boolean save(Company company) {
        return false;
    }

    @Override
    public Company getCompanyById(int id) {
        return null;
    }

    @Override
    public List<Company> getAllCompanies() {
        return null;
    }
}
