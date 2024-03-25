package ru.spb.fibricare.api.personcrud.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.spb.fibricare.api.personcrud.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>,
        PagingAndSortingRepository<Patient, Long> {
    <S extends Patient> S save(S obj);

    boolean existsById(Long id);

    @Query(value = "SELECT p FROM Patient p LEFT JOIN FETCH p.doctor d")
    Optional<Patient> findById(Long id);

    Page<Patient> findAll(Pageable pageable);

    @Query(value = "SELECT p FROM Patient p WHERE p.doctor.id = :id")
    Page<Patient> findAllByDoctorId(PageRequest pageRequest, @Param("id") Long id);

    void deleteById(Long id);
}
