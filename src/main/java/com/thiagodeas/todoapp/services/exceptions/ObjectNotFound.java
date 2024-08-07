package com.thiagodeas.todoapp.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFound extends EntityNotFoundException {

    public ObjectNotFound(String message) {
        super(message);
    }

}
