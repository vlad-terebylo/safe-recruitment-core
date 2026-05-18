package com.tvo.technologies.saferecruitment.model.dto;

import com.tvo.technologies.saferecruitment.model.user.EducationLevel;

public record UserMetadataUpdateDto(
        String name,
        String surname,
        int experience,
        EducationLevel educationLevel,
        String targetPosition,
        String additionalInformation) {
}
