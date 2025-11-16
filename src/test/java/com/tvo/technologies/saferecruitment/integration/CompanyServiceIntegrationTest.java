package com.tvo.technologies.saferecruitment.integration;

import com.tvo.technologies.saferecruitment.exception.CompanyAlreadyExistsException;
import com.tvo.technologies.saferecruitment.exception.CompanyNotFoundException;
import com.tvo.technologies.saferecruitment.exception.InvalidCompanyException;
import com.tvo.technologies.saferecruitment.integration.config.repository.TestCompanyRepository;
import com.tvo.technologies.saferecruitment.model.company.Company;
import com.tvo.technologies.saferecruitment.service.CompanyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyServiceIntegrationTest extends AbstractServiceTest {

    private static final int PRESENT_COMPANY_ID = 1;
    private static final int NOT_PRESENT_COMPANY_ID = -1;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TestCompanyRepository companyRepository;

    @AfterEach
    public void cleanUp() {
        companyRepository.clear();
    }

    @Test
    void should_get_all_companies() {
        Company company1 = new Company("TVO Tech", "Vladyslava 2005", List.of(), "www.tvo-tech.com");
        Company company2 = new Company("TVO Investment", "IT-guy street 46", List.of(), "www.tvo-investment.com");

        companyRepository.save(company1);
        companyRepository.save(company2);

        List<Company> allCompanies = companyService.getAllCompanies();

        assertEquals(List.of(company1, company2), allCompanies);
    }

    @Test
    void should_get_all_companies_if_the_list_is_empty() {
        List<Company> allCompanies = companyService.getAllCompanies();
        assertTrue(allCompanies.isEmpty());
    }

    @Test
    void should_get_all_companies_if_the_there_is_one_company() {
        Company company = new Company("TVO Tech", "Vladyslava 2005", List.of(), "www.tvo-tech.com");
        companyRepository.save(company);

        List<Company> allCompanies = companyService.getAllCompanies();

        assertEquals(1, allCompanies.size());
        assertEquals(company, allCompanies.get(0));
    }

    @Test
    void should_get_company_by_id() {
        Company expectedCompany = new Company("TVO Tech", "Vladyslava 2005", List.of(), "www.tvo-tech.com");
        companyRepository.save(expectedCompany);

        Company actualCompany = companyService.getCompanyById(PRESENT_COMPANY_ID);

        assertEquals(expectedCompany, actualCompany);
    }

    @Test
    void should_throw_exception_if_company_id_is_wrong() {
        assertThrows(CompanyNotFoundException.class, () -> companyService.getCompanyById(PRESENT_COMPANY_ID));
    }

    @Test
    void should_throw_exception_if_company_id_is_negative() {
        assertThrows(CompanyNotFoundException.class, () -> companyService.getCompanyById(NOT_PRESENT_COMPANY_ID));
    }

    @Test
    void should_add_new_company() {
        Company company = new Company("TVO Tech", "Vladyslava 2005", List.of(), "www.tvo-tech.com");

        boolean result = companyService.addNewCompany(company);

        assertTrue(result);
    }

    @Test
    void should_not_add_company_if_it_exists_in_database() {
        Company company = new Company("TVO Tech", "Vladyslava 2005", List.of(), "www.tvo-tech.com");
        companyRepository.save(company);

        assertThrows(CompanyAlreadyExistsException.class, () -> companyService.addNewCompany(company));
    }

    @Test
    void should_throw_exception_if_company_is_invalid() {
        assertThrows(InvalidCompanyException.class, () -> companyService.addNewCompany(null));
    }
}
