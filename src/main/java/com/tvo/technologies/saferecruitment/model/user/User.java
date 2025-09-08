package com.tvo.technologies.saferecruitment.model.user;

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
