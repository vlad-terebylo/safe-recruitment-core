package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.exception.CompanyNotFoundException;
import com.tvo.technologies.saferecruitment.exception.CompanyAlreadyExistsException;
import com.tvo.technologies.saferecruitment.exception.InvalidCompanyException;
import com.tvo.technologies.saferecruitment.model.company.Company;
import com.tvo.technologies.saferecruitment.repository.CompanyRepository;
import com.tvo.technologies.saferecruitment.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    private static final int PRESENT_COMPANY_ID = 1;
    private static final int NOT_PRESENT_COMPANY_ID = -1;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void should_get_all_companies() {
        List<Company> companies = List.of(
                new Company(
                        "TVO Tech",
                        "Vladyslava 2005",
                        List.of(),
                        "www.tvo-tech.com"),
                new Company(
                        "TVO Investment",
                        "IT-guy street 46",
                        List.of(),
                        "www.tvo-investment.com"
                ));


        when(companyRepository.getAllCompanies()).thenReturn(companies);

        List<Company> allCompanies = companyService.getAllCompanies();

        assertEquals(companies, allCompanies);
    }

    @Test
    void should_get_all_companies_if_the_list_is_empty() {
        when(companyRepository.getAllCompanies()).thenReturn(List.of());

        List<Company> emptyCompaniesList = companyService.getAllCompanies();

        assertEquals(List.of(), emptyCompaniesList);
    }

    @Test
    void should_get_all_companies_if_the_there_is_one_company() {
        Company company = new Company(
                "TVO Tech",
                "Vladyslava 2005",
                List.of(),
                "www.tvo-tech.com");

        when(companyRepository.getAllCompanies()).thenReturn(List.of(company));

        List<Company> allCompanies = companyService.getAllCompanies();

        assertEquals(1, allCompanies.size());
        assertEquals(List.of(company), allCompanies);
    }

    @Test
    void should_get_company_by_id() {
        Company company = new Company(
                "TVO Tech",
                "Vladyslava 2005",
                List.of(),
                "www.tvo-tech.com");

        when(companyRepository.getCompanyById(PRESENT_COMPANY_ID)).thenReturn(company);

        Company gotCompany = companyService.getCompanyById(PRESENT_COMPANY_ID);

        assertEquals(company, gotCompany);
    }

    @Test
    void should_throw_exception_if_company_id_is_wrong() {
        when(companyRepository.getCompanyById(PRESENT_COMPANY_ID)).thenThrow(CompanyNotFoundException.class);

        assertThrows(CompanyNotFoundException.class, () -> companyService.getCompanyById(PRESENT_COMPANY_ID));
    }

    @Test
    void should_throw_exception_if_company_id_is_negative() {
        assertThrows(CompanyNotFoundException.class, () -> companyService.getCompanyById(NOT_PRESENT_COMPANY_ID));
    }

    @Test
    void should_add_new_company() {
        Company company = new Company(
                "TVO Tech",
                "Vladyslava 2005",
                List.of(),
                "www.tvo-tech.com");

        when(companyRepository.save(company)).thenReturn(true);

        boolean isAdded = companyService.addNewCompany(company);

        assertTrue(isAdded);

        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void should_not_add_company_if_it_exists_in_database() {
        Company company = new Company(
                "TVO Tech",
                "Vladyslava 2005",
                List.of(),
                "www.tvo-tech.com");

        when(companyRepository.save(company)).thenThrow(CompanyAlreadyExistsException.class);

        assertThrows(CompanyAlreadyExistsException.class, () -> companyService.addNewCompany(company));
    }

    @Test
    void should_throw_exception_if_company_is_invalid() {
        Company company = new Company(
                null,
                "Vladyslava 2005",
                List.of(),
                "www.tvo-tech.com");

        assertThrows(InvalidCompanyException.class, () -> companyService.addNewCompany(company));
    }
}
