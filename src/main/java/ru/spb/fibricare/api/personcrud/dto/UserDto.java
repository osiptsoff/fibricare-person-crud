package ru.spb.fibricare.api.personcrud.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private List<RoleDto> roles;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    public User from() {
        User user = new User();

        user.setId(this.id);
        user.setLogin(this.login);
        user.setPassword(this.password);
        if(this.roles != null) {
            user.setRoles(
                this.roles.stream()
                    .map(r -> r.from())
                    .toList()
            );
        }

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
        if(obj.getRoles() != null) {
            this.setRoles(
            obj.getRoles().stream()
                .map(r -> new RoleDto().fill(r))
                .toList()
            );
        }

        return this;
    }
    
}
