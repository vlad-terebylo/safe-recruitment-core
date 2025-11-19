package com.tvo.technologies.saferecruitment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.tvo.technologies.saferecruitment.service.util.Constants.*;

@Slf4j
@RequiredArgsConstructor
public class GeminiAiValidationService implements AiValidationService {

    private final Client client;
    private final ObjectMapper mapper;

    @Override
    public ValidationResponse validate(VacancyValidationRequest vacancy) {
        String vacancyValidationPrompt = VACANCY_VALIDATION_PROMPT.formatted(
                vacancy.position(), vacancy.description(), vacancy.requiredSkills(),
                vacancy.salary(), vacancy.location(), vacancy.interviewProcessDetails());

        String response = client
                .models
                .generateContent(
                        "gemini-2.5-flash",
                        VACANCY_CONTEXT_PROMPT + "\n" + vacancyValidationPrompt,
                        null).text();

        String formattedResponse = response.substring(response.indexOf("{"), response.indexOf("}"));

        log.info("Returning a response from Gemini: {}", formattedResponse);

        try {
            return mapper.readValue(formattedResponse, ValidationResponse.class);
        } catch (Exception exception) {
            log.error("During mapping vacancy an exception occurred", exception);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ValidationResponse validate(CompanyValidationRequest company) {
        String companyValidationPrompt = COMPANY_VALIDATION_PROMPT.formatted(
                company.title(),
                company.address(),
                company.reviews(),
                company.website()
        );

        String response = client
                .models
                .generateContent(
                        "gemini-2.5-flash",
                        COMPANY_CONTEXT_PROMPT + "\n" + companyValidationPrompt,
                        null).text();

        String formattedResponse = response.substring(response.indexOf("{"), response.indexOf("}"));

        log.info("Returning a response from Gemini: {}", formattedResponse);

        try {
            return mapper.readValue(formattedResponse, ValidationResponse.class);
        } catch (Exception exception) {
            log.error("During mapping company an exception occurred", exception);
            throw new RuntimeException(exception);
        }
    }
}
