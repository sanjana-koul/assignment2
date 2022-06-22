package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TRAIN_EMP")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TraineeEmp {

    @Id
    @Column(name = "ID")
    private long Id;

    @Column(name = "NAME")
    private String Name;

    @Column(name = "SUBJECT")
    private String subject;

}