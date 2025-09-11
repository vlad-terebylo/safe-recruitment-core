package com.tvo.technologies.saferecruitment.service;

import com.tvo.technologies.saferecruitment.exception.CompanyNotFoundException;
import com.tvo.technologies.saferecruitment.exception.InvalidCompanyException;
import com.tvo.technologies.saferecruitment.model.company.Company;
import com.tvo.technologies.saferecruitment.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        log.info("Getting the list of companies");
        return companyRepository.getAllCompanies();
    }

    public Company getCompanyById(int id) {
        if (id < 0) {
            log.error("Getting information about company with id below zero");
            throw new CompanyNotFoundException("Id must not be less than zero");
        }

        log.info("Getting information about company with id: {}", id);
        return companyRepository.getCompanyById(id);
    }

    public boolean addNewCompany(Company company) {
        if (nonValid(company)) {
            log.error("Trying to add invalid company. Some parameters are null");
            throw new InvalidCompanyException("Some of company parameters are null");
        }

        log.info("New company was successfully added");
        return companyRepository.save(company);
    }

    private boolean nonValid(Company company) {
        return Objects.isNull(company.getTitle())
                || Objects.isNull(company.getAddress())
                || Objects.isNull(company.getReviews())
                || Objects.isNull(company.getWebsite());
    }
}
