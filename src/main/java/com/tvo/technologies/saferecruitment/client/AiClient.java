package com.tvo.technologies.saferecruitment.client;

import com.tvo.technologies.saferecruitment.model.validation.ValidationResponse;

public interface AiClient {
    ValidationResponse validate();
}