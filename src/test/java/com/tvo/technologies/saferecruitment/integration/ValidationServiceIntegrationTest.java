package com.tvo.technologies.saferecruitment.integration;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.tvo.technologies.saferecruitment.integration.config.repository.inmemory.TestInMemoryValidationRepository;
import com.tvo.technologies.saferecruitment.model.enums.RiskCategory;
import com.tvo.technologies.saferecruitment.model.enums.ValidationVerdict;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import com.tvo.technologies.saferecruitment.service.ValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpPort = 8080)
public class ValidationServiceIntegrationTest extends AbstractServiceTest {
    private static final String TEST_USER_ID = "573c664f-6d47-44df-8477-09fc89854cec";

    @Autowired
    private TestInMemoryValidationRepository validationRepository;

    @Autowired
    private ValidationService validationService;

    @AfterEach
    public void cleanUp() {
        validationRepository.clear();
    }

    @Test
    void should_get_valid_scam_vacancy_validation_response() {
        String geminiResponse = """
                {
                  "candidates": [
                    {
                      "content": {
                        "parts": [
                          {
                            "text": "{\\"verdict\\": \\"SCAM\\", \\"riskCategory\\": \\"HIGH\\"}"
                          }
                        ]
                      }
                    }
                  ]
                }
                """;


        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(geminiResponse)));

        VacancyValidationRequest vacancyRequest = new VacancyValidationRequest(
                TEST_USER_ID,
                "Barman",
                "description",
                List.of("To serve client"),
                new BigDecimal(5000),
                "Prague",
                true
        );

        ValidationResponse response = validationService.vacancyValidation(TEST_USER_ID, vacancyRequest);

        assertEquals(ValidationVerdict.SCAM, response.verdict());
        assertEquals(RiskCategory.HIGH, response.riskCategory());
    }

    @Test
    void should_get_valid_truthfulness_vacancy_validation_response() {
        String geminiResponse = """
                {
                  "candidates": [
                    {
                      "content": {
                        "parts": [
                          {
                            "text": "{\\"verdict\\": \\"TRUTHFULNESS\\", \\"riskCategory\\": \\"LOW\\"}"
                          }
                        ]
                      }
                    }
                  ]
                }
                """;

        stubFor(post(urlPathMatching("/v1beta/models/gemini-2.5-flash:generateContent"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(geminiResponse)));

        VacancyValidationRequest vacancyRequest = new VacancyValidationRequest(
                TEST_USER_ID,
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

        ValidationResponse response = validationService.vacancyValidation(TEST_USER_ID, vacancyRequest);

        assertEquals(ValidationVerdict.TRUTHFULNESS, response.verdict());
        assertEquals(RiskCategory.LOW, response.riskCategory());
    }

    @Test
    void should_not_get_valid_vacancy_validation_response_if_validation_request_is_invalid() {

    }

    @Test
    void should_throw_exception_if_some_vacancy_validation_request_fields_are_null() {

    }

    @Test
    void should_get_valid_company_validation_response() {

    }

    @Test
    void should_not_get_valid_company_validation_response_if_validation_request_is_invalid() {

    }

    @Test
    void should_throw_exception_if_some_company_validation_request_fields_are_null() {

    }

    @Test
    void should_count_number_of_global_validation_responses() {
        long expected = 1;
        VacancyValidationRequest vacancyRequest = new VacancyValidationRequest(
                TEST_USER_ID,
                "Barman",
                "description",
                List.of("To serve client"),
                new BigDecimal(5000),
                "Prague",
                true
        );

        String geminiResponse = """
                {
                  "candidates": [
                    {
                      "content": {
                        "parts": [
                          {
                            "text": "{\\"verdict\\": \\"SCAM\\", \\"riskCategory\\": \\"HIGH\\"}"
                          }
                        ]
                      }
                    }
                  ]
                }
                """;


        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(geminiResponse)));

        validationService.vacancyValidation(vacancyRequest.userId(), vacancyRequest);

        long actual = validationService.countValidationResponses();

        assertEquals(expected, actual);
    }

    @Test
    void should_count_number_of_validation_responses_for_certain_user() {
        long expected = 1;

        VacancyValidationRequest validVacancyRequest = new VacancyValidationRequest(
                TEST_USER_ID,
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

        String geminiResponse = """
                {
                  "candidates": [
                    {
                      "content": {
                        "parts": [
                          {
                            "text": "{\\"verdict\\": \\"SCAM\\", \\"riskCategory\\": \\"HIGH\\"}"
                          }
                        ]
                      }
                    }
                  ]
                }
                """;


        stubFor(any(anyUrl())
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(geminiResponse)));

        validationService.vacancyValidation(validVacancyRequest.userId(), validVacancyRequest);

        long actual = validationService.countValidationResponses(validVacancyRequest.userId());

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Verdict = {0}")
    @EnumSource(ValidationVerdict.class)
    void should_count_number_of_global_validation_responses_by_verdict(ValidationVerdict verdict) {
        ValidationResponse validResponse = new ValidationResponse(
                ValidationVerdict.TRUTHFULNESS,
                "Descr",
                RiskCategory.LOW,
                List.of());

        ValidationResponse scamResponse = new ValidationResponse(
                ValidationVerdict.SCAM,
                "Descr",
                RiskCategory.HIGH,
                List.of());

        validationRepository.save(TEST_USER_ID, validResponse);
        validationRepository.save(TEST_USER_ID, scamResponse);

        long actual = validationService.countValidationResponsesByVerdict(verdict);

        long expected = 1;
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "Verdict = {0}")
    @EnumSource(ValidationVerdict.class)
    void should_count_number_of_validation_responses_for_certain_user_by_truthful_verdict(ValidationVerdict verdict) {
        ValidationResponse validResponse = new ValidationResponse(
                ValidationVerdict.TRUTHFULNESS,
                "Descr",
                RiskCategory.LOW,
                List.of());

        ValidationResponse scamResponse = new ValidationResponse(
                ValidationVerdict.SCAM,
                "Descr",
                RiskCategory.HIGH,
                List.of());

        validationRepository.save(TEST_USER_ID, validResponse);
        validationRepository.save(TEST_USER_ID, scamResponse);


        long firstActual = validationService.countValidationResponsesByVerdict(TEST_USER_ID, verdict);
        long secondActual = validationService.countValidationResponsesByVerdict(TEST_USER_ID, verdict);

        long expected = 1;

        assertEquals(expected, firstActual);
        assertEquals(expected, secondActual);
    }
}
