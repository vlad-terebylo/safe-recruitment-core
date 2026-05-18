package com.tvo.technologies.saferecruitment.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.util.Objects;

@Data
@With
@AllArgsConstructor
public class UserMetadata {
    private String id;
    private String email;
    private String name;
    private String surname;
    private int experience;
    private EducationLevel education;
    private String targetPosition;
    private String additionalInformation;

    public UserMetadata(String email) {
        this.email = email;
    }

    public UserMetadata(
            String name,
            String surname,
            int experience,
            EducationLevel education,
            String targetPosition,
            String additionalInformation) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.education = education;
        this.targetPosition = targetPosition;
        this.additionalInformation = additionalInformation;
    }

    public UserMetadata(
            String email,
            String name,
            String surname,
            int experience,
            EducationLevel education,
            String targetPosition,
            String additionalInformation) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.education = education;
        this.targetPosition = targetPosition;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof UserMetadata userMetadata)) {
            return false;
        }

        return Objects.equals(this.email, userMetadata.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.email);
    }
}
