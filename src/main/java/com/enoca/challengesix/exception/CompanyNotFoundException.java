package com.enoca.challengesix.exception;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
