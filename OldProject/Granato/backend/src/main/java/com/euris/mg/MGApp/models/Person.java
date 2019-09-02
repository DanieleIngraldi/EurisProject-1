package com.euris.mg.MGApp.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persons")
@Getter
@Setter
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String fiscalCode;
    private String name;
    private String surname;
    LocalDate birthDate;

    public Person() {}

    public Person(Long id, String fiscalCode, String name, String surname, LocalDate birthDate) {
        this.id = id;
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public Person(String fiscalCode, String name, String surname, LocalDate birthDate) {
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }
}