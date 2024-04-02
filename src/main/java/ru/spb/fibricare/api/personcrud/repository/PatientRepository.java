package ru.spb.fibricare.api.personcrud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.spb.fibricare.api.personcrud.model.Patient;

@Repository
public interface PatientRepository extends UserRepository<Patient, Long>,
        PagingAndSortingRepository<Patient, Long> {
    Page<Patient> findAll(Pageable pageable);

    @Query(value = "SELECT p FROM Patient p WHERE p.doctor.id = :id")
    Page<Patient> findAllByDoctorId(Pageable pageable, @Param("id") Long id);
}
