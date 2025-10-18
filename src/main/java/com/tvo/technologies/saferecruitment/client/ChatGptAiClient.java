package com.tvo.technologies.saferecruitment.client;

import com.tvo.technologies.saferecruitment.model.validation.CompanyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.VacancyValidationRequest;
import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;

public class ChatGptAiClient implements AiClient {

    @Override
    public ValidationResponse validate(VacancyValidationRequest vacancy) {
        return null;
    }

    @Override
    public ValidationResponse validate(CompanyValidationRequest company) {
        return null;
    }
}
