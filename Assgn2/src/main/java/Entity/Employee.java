package Entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "EMP_DETAIL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "ID")
    private long Id;

    @Column(name = "NAME")
    private String Name;

    @Column(name = "SUBJECT")
    private String subject;
}