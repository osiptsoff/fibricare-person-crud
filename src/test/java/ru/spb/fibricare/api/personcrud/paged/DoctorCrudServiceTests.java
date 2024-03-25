package ru.spb.fibricare.api.personcrud.paged;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.factory.TestDoctorDtoFactory;
import ru.spb.fibricare.api.personcrud.model.Doctor;
import ru.spb.fibricare.api.personcrud.repository.DoctorRepository;
import ru.spb.fibricare.api.personcrud.service.DoctorCrudService;

@SpringBootTest
public class DoctorCrudServiceTests extends PagedReadingServiceTests<Doctor> {
    @Autowired
    public DoctorCrudServiceTests(DoctorCrudService pagedReadingService,
            DoctorRepository repository, TestDoctorDtoFactory factory) {
        super(pagedReadingService, repository, factory, 3);
    }

    @Override
    protected void verifyPageDto(PageDto<Doctor> pageDto) {
        Assert.isTrue(pageDto.getData().getFirst().from().getId() != null, "Id must not be null");
    }
}
