package ru.spb.fibricare.api.personcrud.factory;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import net.bytebuddy.utility.RandomString;
import ru.spb.fibricare.api.personcrud.dto.DoctorDto;
import ru.spb.fibricare.api.personcrud.dto.UserDto;
import ru.spb.fibricare.api.personcrud.model.Doctor;

@Component
public class TestDoctorDtoFactory implements TestEntityDtoFactory<Doctor, Long> {

    @Override
    public DoctorDto instantiate(Optional<Long> id) {
        DoctorDto dto = new DoctorDto();
        UserDto udto = new UserDto();

        udto.setId(id.isPresent() ? id.get() : null);
        udto.setLogin(RandomString.make(10));
        udto.setPassword(RandomString.make(10));

        dto.setUserInfo(udto);
        dto.setBirthDate(new Date());
        dto.setName(RandomString.make(10));
        dto.setPhoneNumber(RandomString.make(10));

        return dto;
    }
    
}
