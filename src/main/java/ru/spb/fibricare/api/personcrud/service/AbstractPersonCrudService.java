package ru.spb.fibricare.api.personcrud.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import ru.spb.fibricare.api.personcrud.dto.EntityDto;
import ru.spb.fibricare.api.personcrud.dto.factory.EntityDtoFactory;
import ru.spb.fibricare.api.personcrud.model.User;
import ru.spb.fibricare.api.personcrud.repository.UserRepository;
import ru.spb.fibricare.api.personcrud.service.exception.EntityAlreadyExistsException;

@Transactional
public abstract class AbstractPersonCrudService<T extends User, U> extends AbstractCrudService<T, U> {
    protected final PasswordEncoder passwordEncoder;
    protected final UserRepository<T, U> repository;

    public AbstractPersonCrudService(UserRepository<T, U> repository,
            PagingAndSortingRepository<T, U> psRepository,
            EntityDtoFactory<T, U> dtoFactory,
            PasswordEncoder passwordEncoder) {
        super(repository, psRepository, dtoFactory);
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }
    
    @Override
    protected T upsert(T obj) {
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        return super.upsert(obj);
    }

    @Override
    public EntityDto<T, U> save(EntityDto<T, U> obj) throws EntityAlreadyExistsException {
        T domainObj = obj.from();

        if(domainObj.getLogin() != null && repository.existsByLogin(domainObj.getLogin())) {
            throw new EntityAlreadyExistsException(
                domainObj.getClass().getName() + " with given login exists"
            );
        }

        return super.save(obj);
    }
}
