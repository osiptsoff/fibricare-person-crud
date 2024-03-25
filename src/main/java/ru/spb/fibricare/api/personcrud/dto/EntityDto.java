package ru.spb.fibricare.api.personcrud.dto;

public interface EntityDto<T, U> extends ConvertableDto<T>, IdBearingDto<U> {
    EntityDto<T, U> fill(T obj);
}
