package com.tvo.technologies.saferecruitment.exception;

public class VacancyAlreadyExistsException extends RuntimeException{
    public VacancyAlreadyExistsException(String message) {
        super(message);
    }
}
