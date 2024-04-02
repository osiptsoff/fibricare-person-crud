package ru.spb.fibricare.api.personcrud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spb.fibricare.api.personcrud.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements EntityDto<User, Long> {
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public User from() {
        User user = new User();

        user.setId(this.id);
        user.setLogin(this.login);
        user.setPassword(this.password);

        return user;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public UserDto fill(User obj) {
        this.setId(obj.getId());
        this.setLogin(obj.getLogin());
        this.setPassword(obj.getPassword());

        return this;
    }
    
}
