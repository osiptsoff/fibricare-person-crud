package ru.spb.fibricare.api.personcrud.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ru.spb.fibricare.api.personcrud.model.User;

@NoRepositoryBean
public interface UserRepository<T extends User, U> extends CrudRepository<T, U> {
    <S extends T> S save(S obj);

    boolean existsById(Long id);
    
    Optional<T> findById(Long id);

    void deleteById(Long id);
}
