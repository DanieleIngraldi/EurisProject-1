package com.example.EurisProject.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "MODULE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birthDate")
    private Date birthDate;
    @Column(name = "creationTimestamp")
    private Timestamp creationTimestamp;
    @Column(name = "age")
    private Integer age;
    @Column(name = "type")
    private char type;

    private enum Type {
        CHILD,
        OWNER,
        SPOUSE
    }

    public Type getType(){
        switch(type) {
            case 'C':
                return Type.CHILD;
            case 'O':
                return Type.OWNER;
            case 'S':
                return Type.SPOUSE;
            default:
                return null;
        }
    }
}



