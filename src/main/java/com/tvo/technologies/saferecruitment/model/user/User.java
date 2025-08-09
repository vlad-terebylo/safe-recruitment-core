package com.tvo.technologies.saferecruitment.model.user;

import lombok.Data;

@Data
public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private float experience;
    private EducationLevel education;
    private String targetPosition;
    private String additionalInformation;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
