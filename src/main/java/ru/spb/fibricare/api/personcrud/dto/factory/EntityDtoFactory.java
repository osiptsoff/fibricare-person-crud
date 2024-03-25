package ru.spb.fibricare.api.personcrud.dto.factory;

import ru.spb.fibricare.api.personcrud.dto.EntityDto;

public interface EntityDtoFactory<T, U> {
    EntityDto<T, U> instantiate();
    EntityDto<T, U> instantiate(T obj);
}
