package com.tvo.technologies.saferecruitment.service;

import com.openai.client.OpenAIClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.ResponseCreateParams;
import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OpenAiValidationService implements AiValidationService {

    private final OpenAIClient openAIClient;

    @Override
    public ValidationResponse validate(VacancyValidationRequest vacancy) {
        var params = ResponseCreateParams.builder()
                .model(ChatModel.O3)
                .input("Hello, Chat. How is it going?")
                .build();

        var response = openAIClient
                .responses()
                .create(params);

        log.info("Getting response from ChatGPT {}", response.text());

        return null;
    }

    @Override
    public ValidationResponse validate(CompanyValidationRequest company) {
        return null;
    }
}
