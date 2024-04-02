package ru.spb.fibricare.api.personcrud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.spb.fibricare.api.personcrud.service.exception.EntityAlreadyExistsException;
import ru.spb.fibricare.api.personcrud.service.exception.MissingEntityException;
import ru.spb.fibricare.api.personcrud.service.exception.UpdateException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({MissingEntityException.class, EntityAlreadyExistsException.class})
    public ResponseEntity<Map<String, Object>> handle(UpdateException e) {
        return getEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Map<String, Object>> handle(IllegalArgumentException e) {
        return getEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<Map<String, Object>> getEntity(HttpStatus status, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", status.value());
        result.put("error", status.getReasonPhrase());
        result.put("message", message);

        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
