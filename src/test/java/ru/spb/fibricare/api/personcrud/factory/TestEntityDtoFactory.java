package ru.spb.fibricare.api.personcrud.factory;

import java.util.Optional;

import ru.spb.fibricare.api.personcrud.dto.EntityDto;

public interface TestEntityDtoFactory<T, U> {
    EntityDto<T, U> instantiate(Optional<U> id);
}
