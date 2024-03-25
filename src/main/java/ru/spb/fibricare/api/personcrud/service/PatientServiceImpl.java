package ru.spb.fibricare.api.personcrud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.spb.fibricare.api.personcrud.dto.factory.PatientDtoFactory;
import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageRequestDto;
import ru.spb.fibricare.api.personcrud.model.Patient;
import ru.spb.fibricare.api.personcrud.repository.PatientRepository;

@Service
@Transactional
public class PatientServiceImpl extends AbstractCrudService<Patient, Long>
        implements PatientService {
    private final PatientRepository repository;
    private final PatientDtoFactory factory;

    public PatientServiceImpl(PatientRepository repository,
            PatientDtoFactory dtoFactory) {
        super(repository, repository, dtoFactory);
        this.repository = repository;
        this.factory = dtoFactory;
    }

    @Override
    public PageDto<Patient> findAllByDoctorId(PageRequestDto pageRequestDto, Long id) {
        Page<Patient> page;
        var pageRequest = PageRequest.of(pageRequestDto.getPageNumber(), pageRequestDto.getPageSize());

        page = repository.findAllByDoctorId(pageRequest, id);

        var result = page.map(o -> factory.instantiate(o)).getContent();

        return new PageDto<>(result, page.getNumber(), page.getTotalPages());
    }
}
