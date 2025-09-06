package com.tvo.technologies.saferecruitment.model.company;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Company {
    private String title;
    private String address;
    private String website;
    private List<String> reviews;
    private String conclusion;

    public Company(String title, String address, List<String> reviews, String website) {
        this.title = title;
        this.address = address;
        this.reviews = reviews;
        this.website = website;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Company company)) {
            return false;
        }

        return Objects.equals(this.website, company.website);
    }
}
