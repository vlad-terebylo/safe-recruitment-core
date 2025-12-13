package com.tvo.technologies.saferecruitment.unit;

import com.tvo.technologies.saferecruitment.exception.InvalidCompanyValidationRequestException;
import com.tvo.technologies.saferecruitment.exception.InvalidVacancyRequestException;
import com.tvo.technologies.saferecruitment.model.enums.RiskCategory;
import com.tvo.technologies.saferecruitment.model.enums.RedFlags;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import com.tvo.technologies.saferecruitment.repository.ValidationRepository;
import com.tvo.technologies.saferecruitment.service.AiValidationService;
import com.tvo.technologies.saferecruitment.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {
    private static final String TEST_USER_ID = "1";

    @Mock
    private AiValidationService aiClient;

    @Mock
    private ValidationRepository validationRepository;

    @InjectMocks
    private ValidationService validationService;

    @Test
    void should_get_valid_scam_vacancy_validation_response() {
        ValidationResponse validValidationResponse = new ValidationResponse(
                ValidationVerdict.SCAM,
                "Description",
                RiskCategory.HIGH,
                List.of(RedFlags.UNREALISTIC_SALARY));

        VacancyValidationRequest vacancy = new VacancyValidationRequest(
                "573c664f-6d47-44df-8477-09fc89854cec",
                "Barman",
                "description",
                List.of("To serve client"),
                new BigDecimal(5000),
                "Prague",
                true
        );

        when(aiClient.validate(vacancy)).thenReturn(validValidationResponse);

        ValidationResponse actualValidationResponse = validationService.vacancyValidation(TEST_USER_ID, vacancy);

        assertEquals(actualValidationResponse, validValidationResponse);
    }

    @Test
    void should_get_valid_truthfulness_vacancy_validation_response() {
        ValidationResponse validValidationResponse = new ValidationResponse(
                ValidationVerdict.TRUTHFULNESS,
                "Significant risks are not detected",
                RiskCategory.LOW,
                List.of(RedFlags.NO_RED_FLAGS)
        );

        VacancyValidationRequest vacancy = new VacancyValidationRequest(
                "573c664f-6d47-44df-8477-09fc89854cec",
                "Java software engineer",
                "\"Program Start Date: July 2026\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Program Duration: 12 Weeks, Full Time\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Location: Prague \\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Come build community, explore your passions and do your best work at Microsoft with thousands of University interns from every corner of the world. This opportunity will allow you to bring your aspirations, talent, potential—and excitement for the journey ahead.\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"As a Software Engineering Intern, you will work with teammates to solve problems and build innovative software solutions. You will apply your passion for customers and product quality as you provide technical guidance to Technical Program Managers and Product Managers. You will learn and adopt relevant new technologies, tools, methods, and processes to leverage in your solutions. This opportunity will enable you to advance your career by designing, developing, and testing next-generation software that will empower every person and organization on the planet to achieve more.\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"At Microsoft, Interns work on real-world projects in collaboration with teams across the world, while having fun along the way. You’ll be empowered to build community, explore your passions and achieve your goals. This is your chance to bring your solutions and ideas to life while working on cutting-edge technology.\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Microsoft’s mission is to empower every person and every organization on the planet to achieve more. As employees we come together with a growth mindset, innovate to empower others, and collaborate to realize our shared goals. Each day we build on our values of respect, integrity, and accountability to create a culture of inclusion where everyone can thrive at work and beyond.\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Responsibilities\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Apply engineering principles to solve complex problems through sound and creative engineering. \\n\" +\n" +
                        "                        \"Work with appropriate stakeholders to determine user requirements for a feature.\\n\" +\n" +
                        "                        \"Quickly learns new engineering methods and incorporates them into work processes. \\n\" +\n" +
                        "                        \"Seek feedback and apply internal or industry best practices to improve technical solutions. \\n\" +\n" +
                        "                        \"Demonstrate skill in time management and completing software projects in a cooperative team environment.\\n\" +\n" +
                        "                        \"Review current developments and proactively seek new knowledge that will improve the availability, reliability, efficiency, observability, and performance of products while also driving consistency in monitoring and operations at scale.\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Qualifications\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Required Qualifications:\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Currently pursuing Bachelor’s or Master’s degree in Computer Science, Engineering, or related field.\\n\" +\n" +
                        "                        \"Must have at least 1 semester/term remaining following the completion of the internship.\\n\" +\n" +
                        "                        \"One year of programming experience in an object-oriented language (e.g., C#, Python, Java, C++, JavaScript, etc.).\\n\" +\n" +
                        "                        \"You must be legally authorised to work in Czech Republic to be eligible for this role. (Legally authorised = has citizenship or has been granted a valid visa or work permit).\\n\" +\n" +
                        "                        \"Fluency in English.\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Preferred Qualifications\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Ability to demonstrate an understanding of computer science fundamentals, including data structures and algorithms.\\n\" +\n" +
                        "                        \"Excellent communication skills and ability to work effectively in a team environment.\\n\" +\n" +
                        "                        \"Exposure to cloud platforms.\\n\" +\n" +
                        "                        \"Interest in identity, authentication, and security technologies OR\\n\" +\n" +
                        "                        \"Interest in integrating AI/ML into productivity tools.\\n\" +\n" +
                        "                        \"\\n\" +\n" +
                        "                        \"Microsoft is an equal opportunity employer.\" +\n" +
                        "                        \"Consistent with applicable law, all qualified applicants will receive consideration for employment\" +\n" +
                        "                        \"without regard to age, ancestry, citizenship, color, family or medical care leave, gender identity or expression, genetic information, immigration status,\" +\n" +
                        "                        \" marital status, medical condition, national origin, physical or mental disability, political affiliation, protected veteran or military status, race, ethnicity,\" +\n" +
                        "                        \" religion, sex (including pregnancy), sexual orientation, or any other characteristic protected by applicable local laws, regulations and ordinances.\" +\n" +
                        "                        \" If you need assistance and/or a reasonable accommodation due to a disability during the application process, read more about requesting accommodations.\"",
                List.of("Repair bugs"),
                new BigDecimal(1200),
                "Prague",
                true
        );

        when(aiClient.validate(vacancy)).thenReturn(validValidationResponse);

        ValidationResponse actualValidationResponse = validationService.vacancyValidation(TEST_USER_ID, vacancy);

        assertEquals(actualValidationResponse, validValidationResponse);
    }

    @Test
    void should_not_get_valid_vacancy_validation_response_if_validation_request_is_invalid() {
        assertThrows(InvalidVacancyRequestException.class, () -> validationService.vacancyValidation("1", null));
    }

    @Test
    void should_throw_exception_if_some_vacancy_validation_request_fields_are_null() {
        VacancyValidationRequest vacancyValidationRequest = new VacancyValidationRequest(
                "573c664f-6d47-44df-8477-09fc89854cec",
                "Senior software developer",
                null,
                null,
                new BigDecimal(5000),
                null, true
        );

        assertThrows(InvalidVacancyRequestException.class, () -> validationService.vacancyValidation(TEST_USER_ID, vacancyValidationRequest));
    }

    @Test
    void should_get_valid_company_validation_response() {
        CompanyValidationRequest companyValidationRequest = new CompanyValidationRequest(
                "573c664f-6d47-44df-8477-09fc89854cec",
                "Microsoft Corporation",
                "Redmond, 980 52, USA",
                "https://www.microsoft.com/",
                List.of("Good work conditions", "Perfect office")
        );

        ValidationResponse expectedCompanyValidationResponse = new ValidationResponse(
                ValidationVerdict.TRUTHFULNESS,
                "Corporation with sufficient benefits.Good to work but very hard for entry",
                RiskCategory.LOW,
                List.of(RedFlags.NO_RED_FLAGS)
        );

        when(aiClient.validate(companyValidationRequest)).thenReturn(expectedCompanyValidationResponse);

        ValidationResponse actualCompanyValidationResponse = validationService.companyValidation(TEST_USER_ID, companyValidationRequest);

        assertEquals(expectedCompanyValidationResponse, actualCompanyValidationResponse);
    }

    @Test
    void should_not_get_valid_company_validation_response_if_validation_request_is_invalid() {
        assertThrows(InvalidCompanyValidationRequestException.class, () -> validationService.companyValidation(TEST_USER_ID, null));
    }

    @Test
    void should_throw_exception_if_some_company_validation_request_fields_are_null() {
        CompanyValidationRequest companyValidationRequest = new CompanyValidationRequest(
                "573c664f-6d47-44df-8477-09fc89854cec",
                "Google",
                null,
                null,
                null
        );

        assertThrows(InvalidCompanyValidationRequestException.class, () -> validationService.companyValidation(TEST_USER_ID, companyValidationRequest));
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 12})
    void should_count_number_of_global_validation_responses(long expected) {

        when(validationRepository.count()).thenReturn(expected);

        long actual = validationService.countValidationResponses();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 7})
    void should_count_number_of_validation_responses_for_certain_user(long expected) {
        String userId = "1";

        when(validationRepository.count(userId)).thenReturn(expected);

        long actual = validationService.countValidationResponses(userId);

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Verdict = {0}")
    @EnumSource(ValidationVerdict.class)
    void should_count_number_of_global_validation_responses_by_verdict(ValidationVerdict verdict) {
        long expected = 3;

        when(validationRepository.countByVerdict(verdict)).thenReturn(expected);

        long actual = validationService.countValidationResponsesByVerdict(verdict);

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Verdict = {0}")
    @EnumSource(ValidationVerdict.class)
    void should_count_number_of_validation_responses_for_certain_user_by_truthful_verdict(ValidationVerdict verdict) {
        long expected = 3;
        String userId = "1";

        when(validationRepository.countByVerdict(userId, verdict)).thenReturn(expected);

        long actual = validationService.countValidationResponsesByVerdict(userId, verdict);

        assertEquals(expected, actual);
    }
}
