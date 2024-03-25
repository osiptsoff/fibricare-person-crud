package ru.spb.fibricare.api.personcrud.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.spb.fibricare.api.personcrud.factory.TestDoctorDtoFactory;
import ru.spb.fibricare.api.personcrud.model.Doctor;
import ru.spb.fibricare.api.personcrud.service.DoctorCrudService;

@SpringBootTest
public class DoctorCrudServiceTests extends CrudServiceTests<Doctor, Long> {
    @Autowired
    public DoctorCrudServiceTests(DoctorCrudService service, TestDoctorDtoFactory factory) {
        super(service, factory);
    }
}
