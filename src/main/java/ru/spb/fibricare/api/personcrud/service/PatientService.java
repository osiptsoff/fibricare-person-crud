package ru.spb.fibricare.api.personcrud.service;


import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageRequestDto;
import ru.spb.fibricare.api.personcrud.model.Patient;

public interface PatientService extends CrudService<Patient, Long>,
        PagedReadingService<Patient> {
    PageDto<Patient> findAllByDoctorId(PageRequestDto pageRequestDto, Long id);
}
