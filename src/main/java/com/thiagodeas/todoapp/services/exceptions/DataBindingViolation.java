package com.thiagodeas.todoapp.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataBindingViolation extends DataIntegrityViolationException {

    public DataBindingViolation(String message) {
        super(message);
    }

}

