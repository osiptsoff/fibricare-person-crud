package ru.spb.fibricare.api.personcrud.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.spb.fibricare.api.personcrud.dto.EntityDto;
import ru.spb.fibricare.api.personcrud.dto.factory.EntityDtoFactory;
import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageRequestDto;
import ru.spb.fibricare.api.personcrud.service.exception.MissingEntityException;

@Transactional
@RequiredArgsConstructor
public abstract class AbstractCrudService<T, U> implements CrudService<T, U>, PagedReadingService<T> {
    protected final CrudRepository<T, U> repository;
    protected final PagingAndSortingRepository<T, U> psRepository;
    protected final EntityDtoFactory<T, U> dtoFactory;

    @Override
    public EntityDto<T, U> save(EntityDto<T, U> obj) throws DuplicateKeyException,
            DataIntegrityViolationException {
        T domainObj = obj.from();

        if(obj.getId() != null) {
            throw new DuplicateKeyException(
                "Id must not be assigned"
            );
        }

        return dtoFactory.instantiate(upsert(domainObj));
    }

    protected T upsert(T obj) {
        return repository.save(obj);
    }

    @Override
    public EntityDto<T, U> update(EntityDto<T, U> obj) throws MissingEntityException,
            DataIntegrityViolationException {
        T domainObj = obj.from();

        if(obj.getId() == null || !repository.existsById(obj.getId())) {
            throw new MissingEntityException(domainObj.getClass().getSimpleName() + " not found");
        }

        return obj.fill(upsert(domainObj));
    }

    @Override
    public void deleteOneById(U id) throws MissingEntityException {
        if(id == null || !repository.existsById(id)) {
            throw new MissingEntityException("Entity not found");
        }

        repository.deleteById(id);
    }

    @Override
    public EntityDto<T, U> getOneByid(U id) throws MissingEntityException  {
        if(id == null) {
            throw new MissingEntityException("Entity not found");
        }

        var result = dtoFactory.instantiate();
        
        Optional<T> optional = repository.findById(id);
        if(!optional.isPresent()) {
            throw new MissingEntityException("Entity not found");
        }

        return result.fill(optional.get());
    }

    @Override
    public PageDto<T> findPage(PageRequestDto pageRequestDto) {
        Page<T> page;
        var pageRequest = PageRequest.of(pageRequestDto.getPageNumber(), pageRequestDto.getPageSize());

        page = psRepository.findAll(pageRequest);

        var result = page.map(o -> dtoFactory.instantiate(o)).getContent();

        return new PageDto<>(result, page.getNumber(), page.getTotalPages());
    }
}
