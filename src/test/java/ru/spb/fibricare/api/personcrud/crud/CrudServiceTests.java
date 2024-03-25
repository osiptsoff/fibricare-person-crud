package ru.spb.fibricare.api.personcrud.crud;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;
import ru.spb.fibricare.api.personcrud.factory.TestEntityDtoFactory;
import ru.spb.fibricare.api.personcrud.service.CrudService;
import ru.spb.fibricare.api.personcrud.service.exception.MissingEntityException;

@Transactional
@RequiredArgsConstructor
public abstract class CrudServiceTests<T, U> {
    private final CrudService<T, U> crudService;
    private final TestEntityDtoFactory<T, U> factory;

    @Test
    public final void saveTest() {
        var entityDto = factory.instantiate(Optional.empty());

        var savedEntityDto = crudService.save(entityDto);
        Assert.notNull(savedEntityDto.getId(), "Persisted entity must have id assigned");
    }

    @Test
    public final void updateTest() {
        var savedEntityDto = factory.instantiate(Optional.empty());
        savedEntityDto = crudService.save(savedEntityDto);

        var modifiedEntityDto = factory.instantiate(Optional.of(savedEntityDto.getId()));
        modifiedEntityDto = crudService.update(modifiedEntityDto);

        Assert.isTrue(savedEntityDto.getId().equals(savedEntityDto.getId()), "Id must not change");
    }

    @Test
    public final void getOneByIdTest() {
        var savedEntityDto = factory.instantiate(Optional.empty());
        savedEntityDto = crudService.save(savedEntityDto);

        var extractedEntityDto = crudService.getOneByid(savedEntityDto.getId());

        Assert.isTrue(savedEntityDto.equals(extractedEntityDto), "Entities must be equal");
    }

    @Test
    public final void deleteTest() {
        var savedEntityDto = factory.instantiate(Optional.empty());
        savedEntityDto = crudService.save(savedEntityDto);

        U id = savedEntityDto.getId();

        crudService.deleteOneById(id);
        try {
            crudService.getOneByid(id);
            Assert.isTrue(false, "Entity must not be persistent");
        } catch(MissingEntityException e) {

        }
    }
}
