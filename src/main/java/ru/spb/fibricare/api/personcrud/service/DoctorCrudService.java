package ru.spb.fibricare.api.personcrud.service;

import org.springframework.stereotype.Service;

import ru.spb.fibricare.api.personcrud.dto.factory.DoctorDtoFactory;
import ru.spb.fibricare.api.personcrud.model.Doctor;
import ru.spb.fibricare.api.personcrud.repository.DoctorRepository;

@Service
public class DoctorCrudService extends AbstractCrudService<Doctor, Long> {
    public DoctorCrudService(DoctorRepository repository,
            DoctorDtoFactory dtoFactory) {
        super(repository, repository, dtoFactory);
    }
}
