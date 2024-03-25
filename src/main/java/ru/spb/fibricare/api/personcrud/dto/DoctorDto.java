package ru.spb.fibricare.api.personcrud.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spb.fibricare.api.personcrud.model.Doctor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto implements EntityDto<Doctor, Long> {
    @NotNull
    private UserDto userInfo;
    @NotBlank
    private String name;
    @NotNull
    private Date birthDate;
    @NotBlank
    private String phoneNumber;

    @Override
    public Doctor from() {
        Doctor doctor = new Doctor();

        doctor.setId(userInfo.getId());
        doctor.setLogin(userInfo.getLogin());
        doctor.setPassword(userInfo.getPassword());
        doctor.setName(this.name);
        doctor.setBirthDate(this.birthDate);
        doctor.setPhoneNumber(this.phoneNumber);

        return doctor;
    }

    @Override
    public Long getId() {
        return this.userInfo.getId();
    }

    @Override
    public DoctorDto fill(Doctor obj) {
        this.setUserInfo(new UserDto().fill(obj));
        this.setName(obj.getName());
        this.setBirthDate(obj.getBirthDate());
        this.setPhoneNumber(obj.getPhoneNumber());

        return this;
    }
}
