package ru.spb.fibricare.api.personcrud.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "person", name = "patient")
public class Patient extends User {
    @Column(name = "name")
    protected String name;
    @Column(name = "birth_date")
    protected Date birthDate;
    @Column(name = "phone_number")
    protected String phoneNumber;
    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    protected Doctor doctor;
    @Column(name = "snils")
    protected Long snils;
    @Column(name = "medcard")
    protected Long medcard;
    @Column(name = "omipolicy")
    protected Long omiPolicy;
    @Column(name = "sex")
    protected Byte sex;
}
