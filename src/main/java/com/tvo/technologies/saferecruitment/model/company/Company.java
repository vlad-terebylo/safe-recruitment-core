package com.tvo.technologies.saferecruitment.model.company;

import lombok.Data;

import java.util.List;

@Data
public class Company {
    private String title;
    private String address;
    private List<String> reviews;
    private String conclusion;

    public Company(String title, String address, List<String> reviews) {
        this.title = title;
        this.address = address;
        this.reviews = reviews;
    }
}
