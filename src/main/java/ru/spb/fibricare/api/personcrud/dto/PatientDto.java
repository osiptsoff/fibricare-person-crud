package ru.spb.fibricare.api.personcrud.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spb.fibricare.api.personcrud.model.Doctor;
import ru.spb.fibricare.api.personcrud.model.Patient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto implements EntityDto<Patient, Long> {
    @NotNull
    @Valid
    private UserDto userInfo;
    @NotBlank
    private String name;
    @NotNull
    private Date birthDate;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private Long doctorId;
    @NotNull
    private Long snils;
    @NotNull
    private Long medcard;
    @NotNull
    private Long omiPolicy;
    @NotNull
    private Byte sex;

    @Override
    public Patient from() {
        Patient patient = new Patient();

        Doctor doctor = null;
        if(this.doctorId != null) {
            doctor = new Doctor();
            doctor.setId(this.doctorId);
        }
        
        patient.setId(userInfo.getId());
        patient.setLogin(userInfo.getLogin());
        patient.setPassword(userInfo.getPassword());
        patient.setName(this.name);
        patient.setBirthDate(this.birthDate);
        patient.setPhoneNumber(this.phoneNumber);
        patient.setDoctor(doctor);
        patient.setSnils(this.snils);
        patient.setMedcard(this.medcard);
        patient.setOmiPolicy(this.omiPolicy);
        patient.setSex(this.sex);

        return patient;
    }

    @Override
    @JsonIgnore
    public Long getId() {
        return this.userInfo.getId();
    }

    @Override
    public PatientDto fill(Patient obj) {
        this.setUserInfo(new UserDto().fill(obj));
        this.setName(obj.getName());
        this.setBirthDate(obj.getBirthDate());
        this.setPhoneNumber(obj.getPhoneNumber());
        this.setDoctorId(obj.getDoctor() == null ? null : obj.getDoctor().getId());
        this.setSnils(obj.getSnils());
        this.setMedcard(obj.getMedcard());
        this.setOmiPolicy(obj.getOmiPolicy());
        this.setSex(obj.getSex());

        return this;
    }
    
}
