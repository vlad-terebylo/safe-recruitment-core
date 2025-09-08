package com.tvo.technologies.saferecruitment.exception;

public class ThisCompanyAlreadyExistsException extends RuntimeException {
    public ThisCompanyAlreadyExistsException(String message) {
        super(message);
    }
}
