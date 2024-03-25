package ru.spb.fibricare.api.personcrud.paged;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.factory.TestPatientDtoFactory;
import ru.spb.fibricare.api.personcrud.model.Patient;
import ru.spb.fibricare.api.personcrud.repository.PatientRepository;
import ru.spb.fibricare.api.personcrud.service.PatientServiceImpl;

@SpringBootTest
public class PatientServiceImplPagedReadingTest extends PagedReadingServiceTests<Patient> {
    @Autowired
    public PatientServiceImplPagedReadingTest(PatientServiceImpl pagedReadingService,
            PatientRepository crudRepository, TestPatientDtoFactory factory) {
        super(pagedReadingService, crudRepository, factory, 3);
    }

    @Override
    protected void verifyPageDto(PageDto<Patient> pageDto) {
        Assert.isTrue(pageDto.getData().getFirst().from().getId() != null, "Id must not be null");
    }
    
}
