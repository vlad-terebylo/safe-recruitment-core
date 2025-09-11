package com.tvo.technologies.saferecruitment.exception;

public class VacancyNotFoundException extends RuntimeException{
    public VacancyNotFoundException(String message) {
        super(message);
    }
}
