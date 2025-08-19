package com.tvo.technologies.saferecruitment.model.validation;

import java.util.List;

public record CompanyValidationRequest(
        String title,
        String address,
        String website,
        List<String> reviews) {
}
