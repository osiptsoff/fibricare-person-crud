package ru.spb.fibricare.api.personcrud.dto.page;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;
}
