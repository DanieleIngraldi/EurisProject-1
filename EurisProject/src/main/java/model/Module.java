package model;

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



