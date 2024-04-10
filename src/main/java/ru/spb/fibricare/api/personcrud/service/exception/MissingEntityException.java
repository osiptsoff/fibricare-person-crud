package ru.spb.fibricare.api.personcrud.service.exception;

import org.springframework.dao.NonTransientDataAccessException;

import lombok.experimental.StandardException;

@StandardException
public class MissingEntityException extends NonTransientDataAccessException {
    
}
