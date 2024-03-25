package ru.spb.fibricare.api.personcrud.model;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "person", name = "doctor")
public class Doctor extends User {
    @Column(name = "name")
    protected String name;
    @Column(name = "birth_date")
    protected Date birthDate;
    @Column(name = "phone_number")
    protected String phoneNumber;
    @OneToMany(mappedBy = "doctor")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    protected Collection<Patient> patients;
}
