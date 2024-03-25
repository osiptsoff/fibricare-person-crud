package ru.spb.fibricare.api.personcrud.service;

import ru.spb.fibricare.api.personcrud.dto.EntityDto;
import ru.spb.fibricare.api.personcrud.service.exception.EntityAlreadyExistsException;
import ru.spb.fibricare.api.personcrud.service.exception.MissingEntityException;

public interface CrudService<T, U> {
    EntityDto<T, U> save(EntityDto<T, U> obj) throws EntityAlreadyExistsException;
    EntityDto<T, U> update(EntityDto<T, U> obj) throws MissingEntityException;
    void deleteOneById(U id) throws MissingEntityException;
    EntityDto<T, U> getOneByid(U id) throws MissingEntityException;
}
