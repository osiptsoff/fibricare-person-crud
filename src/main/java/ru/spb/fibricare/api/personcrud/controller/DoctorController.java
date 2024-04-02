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
import ru.spb.fibricare.api.personcrud.dto.DoctorDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageDto;
import ru.spb.fibricare.api.personcrud.dto.page.PageRequestDto;
import ru.spb.fibricare.api.personcrud.model.Doctor;
import ru.spb.fibricare.api.personcrud.service.CrudService;
import ru.spb.fibricare.api.personcrud.service.PagedReadingService;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
@Validated
public class DoctorController {
    private final CrudService<Doctor, Long> service;
    private final PagedReadingService<Doctor> prService;

    @GetMapping("")
    public PageDto<Doctor> read(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return prService.findPage(new PageRequestDto(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ConvertableDto<Doctor> read(@PathVariable Long id) {
        return service.getOneByid(id);
    }

    @PostMapping("")
    public ConvertableDto<Doctor> create(@Valid @RequestBody DoctorDto dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public ConvertableDto<Doctor> update(@Valid @RequestBody DoctorDto dto,
            @PathVariable Long id) {
        dto.getUserInfo().setId(id);

        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteOneById(id);
    }
}
