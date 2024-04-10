package ru.spb.fibricare.api.personcrud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.spb.fibricare.api.personcrud.service.exception.MissingEntityException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({DuplicateKeyException.class, MissingEntityException.class})
    public ResponseEntity<Map<String, Object>> handle(NonTransientDataAccessException e) {
        return getEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Map<String, Object>> handle(IllegalArgumentException e) {
        return getEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, Object>> handle(DataIntegrityViolationException e) {
        var strs = e.getMostSpecificCause().getMessage().split("\n");
        return getEntity(HttpStatus.BAD_REQUEST, strs[1].strip());
    }

    private ResponseEntity<Map<String, Object>> getEntity(HttpStatus status, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", status.value());
        result.put("error", status.getReasonPhrase());
        result.put("message", message);

        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
