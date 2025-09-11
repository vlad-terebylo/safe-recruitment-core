package com.tvo.technologies.saferecruitment.model.vacancy;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
public class Vacancy {
    private String position;
    private String description;
    private List<String> requiredSkills;
    private BigDecimal salary;
    private String conclusion;

    public Vacancy(String position, String description, List<String> requiredSkills) {
        this.position = position;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Vacancy vacancy)) {
            return false;
        }

        return Objects.equals(this.description, vacancy.description)
                && Objects.equals(this.position, vacancy.position);
    }
}
