package ru.spb.fibricare.api.personcrud;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageRequestDto;
import ru.spb.fibricare.api.personcrud.factory.TestDoctorDtoFactory;
import ru.spb.fibricare.api.personcrud.factory.TestPatientDtoFactory;
import ru.spb.fibricare.api.personcrud.model.Doctor;
import ru.spb.fibricare.api.personcrud.model.Patient;
import ru.spb.fibricare.api.personcrud.service.CrudService;
import ru.spb.fibricare.api.personcrud.service.PatientService;

@SpringBootTest
@Transactional
public class PatientServiceSpecificTests {
    protected final static Integer ITEMS_COUNT = 4;

    protected final PatientService patientService;
    protected final CrudService<Doctor, Long> doctorService;

    protected final TestPatientDtoFactory patientFactory;
    protected final TestDoctorDtoFactory doctorFactory;

    @Autowired
    public PatientServiceSpecificTests(PatientService patientService,
            CrudService<Doctor, Long> doctorService, TestPatientDtoFactory patientFactory,
            TestDoctorDtoFactory doctorFactory) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.patientFactory = patientFactory;
        this.doctorFactory = doctorFactory;
    }

    @Test
    public final void findPageTest() {
        var doctorDto = doctorFactory.instantiate(Optional.empty());
        var doctorId = doctorService.save(doctorDto).getId();
        Assert.isTrue(doctorId != null, "Doctor must have id");

        for(int i = 0; i < ITEMS_COUNT; i++) {
            var dto = patientFactory.instantiate(Optional.empty());
            dto.setDoctorId(doctorId);
            patientService.save(dto);
        }

        PageDto<Patient> pageDto = patientService.findAllByDoctorId(
            new PageRequestDto(0, ITEMS_COUNT), doctorId
        );

        Assert.isTrue(pageDto != null, "Page DTO must not be null");
        Assert.isTrue(pageDto.getData() != null, "Content array must not be null");
        Assert.isTrue(pageDto.getData().size() > 0, "Page must not be empty");

        pageDto.getData().forEach(p -> {
            Patient patient = p.from();
            Assert.isTrue(patient.getId() != null, "Id must not be null");
            Assert.isTrue(patient.getDoctor().getId() != null, "Must be associated to doctor");
        });
        
    }
}
