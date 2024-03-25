package ru.spb.fibricare.api.personcrud.dto.factory;

import org.springframework.stereotype.Component;

import ru.spb.fibricare.api.personcrud.dto.PatientDto;
import ru.spb.fibricare.api.personcrud.model.Patient;

@Component
public class PatientDtoFactory implements EntityDtoFactory<Patient, Long> {
    @Override
    public PatientDto instantiate() {
        return new PatientDto();
    }

    @Override
    public PatientDto instantiate(Patient obj) {
        return new PatientDto().fill(obj);
    }
    
}
