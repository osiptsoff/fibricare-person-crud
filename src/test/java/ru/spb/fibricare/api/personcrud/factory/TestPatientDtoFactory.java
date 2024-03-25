package ru.spb.fibricare.api.personcrud.factory;

import java.util.Date;
import java.util.Optional;
import java.util.random.RandomGenerator;

import org.springframework.stereotype.Component;

import net.bytebuddy.utility.RandomString;
import ru.spb.fibricare.api.personcrud.dto.PatientDto;
import ru.spb.fibricare.api.personcrud.dto.UserDto;
import ru.spb.fibricare.api.personcrud.model.Patient;

@Component
public class TestPatientDtoFactory implements TestEntityDtoFactory<Patient, Long> {

    @Override
    public PatientDto instantiate(Optional<Long> id) {
        PatientDto dto = new PatientDto();
        UserDto udto = new UserDto();

        udto.setId(id.isPresent() ? id.get() : null);
        udto.setLogin(RandomString.make(10));
        udto.setPassword(RandomString.make(10));

        dto.setUserInfo(udto);
        dto.setBirthDate(new Date());
        dto.setName(RandomString.make(10));
        dto.setPhoneNumber(RandomString.make(10));
        
        dto.setMedcard(RandomGenerator.getDefault().nextLong());
        dto.setOmiPolicy(RandomGenerator.getDefault().nextLong());
        dto.setSex((byte)2);
        dto.setSnils(RandomGenerator.getDefault().nextLong());

        return dto;
    }
    
}
