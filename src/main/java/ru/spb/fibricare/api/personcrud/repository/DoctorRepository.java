package ru.spb.fibricare.api.personcrud.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import ru.spb.fibricare.api.personcrud.model.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long>, PagingAndSortingRepository<Doctor, Long> {
    <S extends Doctor> S save(S obj);

    boolean existsById(Long id);
    
    Optional<Doctor> findById(Long id);

    Page<Doctor> findAll(Pageable pageable);

    @Procedure(name = "person.DeleteUser")
    @Transactional
    void deleteById(Long id);
}
