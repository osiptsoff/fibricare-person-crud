package ru.spb.fibricare.api.personcrud.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.spb.fibricare.api.personcrud.factory.TestPatientDtoFactory;
import ru.spb.fibricare.api.personcrud.model.Patient;
import ru.spb.fibricare.api.personcrud.service.PatientServiceImpl;

@SpringBootTest
public class PatientServiceImplCrudTests extends CrudServiceTests<Patient, Long> {
    @Autowired
    public PatientServiceImplCrudTests(PatientServiceImpl service, TestPatientDtoFactory factory) {
        super(service, factory);
    }
}
