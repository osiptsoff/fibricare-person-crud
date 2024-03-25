package ru.spb.fibricare.api.personcrud.dto;

public interface ConvertableDto<T> {
    T from();
    ConvertableDto<T> fill(T obj);
}
