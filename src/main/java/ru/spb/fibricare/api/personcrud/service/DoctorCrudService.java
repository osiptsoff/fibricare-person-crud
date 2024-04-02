package ru.spb.fibricare.api.personcrud.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.spb.fibricare.api.personcrud.dto.factory.DoctorDtoFactory;
import ru.spb.fibricare.api.personcrud.model.Doctor;
import ru.spb.fibricare.api.personcrud.repository.DoctorRepository;

@Service
public class DoctorCrudService extends AbstractCrudService<Doctor, Long> {
    private final PasswordEncoder passwordEncoder;

    public DoctorCrudService(DoctorRepository repository,
            DoctorDtoFactory dtoFactory, PasswordEncoder passwordEncoder) {
        super(repository, repository, dtoFactory);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected Doctor upsert(Doctor obj) {
        obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        return super.upsert(obj);
    }
}
