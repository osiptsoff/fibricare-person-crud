package ru.spb.fibricare.api.personcrud.dto.factory;

import org.springframework.stereotype.Component;

import ru.spb.fibricare.api.personcrud.dto.DoctorDto;
import ru.spb.fibricare.api.personcrud.model.Doctor;

@Component
public class DoctorDtoFactory implements EntityDtoFactory<Doctor, Long> {
    @Override
    public DoctorDto instantiate() {
        return new DoctorDto();
    }

    @Override
    public DoctorDto instantiate(Doctor obj) {
        return new DoctorDto().fill(obj);
    }
    
}
