package ru.spb.fibricare.api.personcrud.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.spb.fibricare.api.personcrud.dto.ConvertableDto;
import ru.spb.fibricare.api.personcrud.dto.PatientDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageRequestDto;
import ru.spb.fibricare.api.personcrud.model.Patient;
import ru.spb.fibricare.api.personcrud.service.PagedReadingService;
import ru.spb.fibricare.api.personcrud.service.PatientService;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@Validated
public class PatientController {
    private final PatientService service;
    private final PagedReadingService<Patient> prService;

    @GetMapping("")
    public PageDto<Patient> read(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return prService.findPage(new PageRequestDto(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ConvertableDto<Patient> read(@PathVariable Long id) {
        return service.getOneByid(id);
    }

    @GetMapping("/of/{doctorId}")
    public PageDto<Patient> readAllByDoctorId(@RequestParam Integer pageNumber,
            @RequestParam Integer pageSize, @PathVariable Long doctorId) {
        PageRequestDto pageRequestDto = new PageRequestDto(pageNumber, pageSize);

        return service.findAllByDoctorId(pageRequestDto, doctorId);
    }

    @PostMapping("")
    public ConvertableDto<Patient> create(@Valid @RequestBody PatientDto dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public ConvertableDto<Patient> update(@Valid @RequestBody PatientDto dto,
            @PathVariable Long id) {
        dto.getUserInfo().setId(id);

        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteOneById(id);
    }
}
