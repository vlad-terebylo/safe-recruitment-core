package com.tvo.technologies.saferecruitment.model.user;

import com.tvo.technologies.saferecruitment.model.dto.ChangePsswdRequestDto;
import com.tvo.technologies.saferecruitment.model.dto.UserUpdateDto;
import lombok.Data;

import java.util.Objects;

@Data
public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private int experience;
    private EducationLevel education;
    private String targetPosition;
    private String additionalInformation;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String surname, int experience, EducationLevel education, String targetPosition, String additionalInformation) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.education = education;
        this.targetPosition = targetPosition;
        this.additionalInformation = additionalInformation;
    }

    public User(String email, String password, String name, String surname, int experience, EducationLevel education, String targetPosition, String additionalInformation) {
        this.email = email;
        this.password = password;
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
        if (!(object instanceof User user)) {
            return false;
        }

        return Objects.equals(this.email, user.email);
    }
}
