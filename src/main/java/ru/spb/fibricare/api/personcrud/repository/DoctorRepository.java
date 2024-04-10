package ru.spb.fibricare.api.personcrud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ru.spb.fibricare.api.personcrud.model.Doctor;

@Repository
public interface DoctorRepository extends UserRepository<Doctor, Long>,
        PagingAndSortingRepository<Doctor, Long> {
    Page<Doctor> findAll(Pageable pageable);
}
