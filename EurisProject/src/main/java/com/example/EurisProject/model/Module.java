package com.example.EurisProject.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private Date birthDate;
    private Timestamp creationTimestamp;
    private Integer Age;

    private enum Type {
        CHILD,
        OWNER,
        SPOUSE
    }


}



