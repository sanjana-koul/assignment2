package repository;

import Entity.TraineeEmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeEmpRepo extends JpaRepository<TraineeEmp, Long> {

}