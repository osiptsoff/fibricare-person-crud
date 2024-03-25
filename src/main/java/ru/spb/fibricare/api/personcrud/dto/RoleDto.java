package ru.spb.fibricare.api.personcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spb.fibricare.api.personcrud.model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements ConvertableDto<Role> {
    private String name;

    @Override
    public Role from() {
        Role role = new Role();

        role.setName(this.name);

        return role;
    }

    @Override
    public RoleDto fill(Role obj) {
        this.name = obj.getName();

        return this;
    }
    
}
