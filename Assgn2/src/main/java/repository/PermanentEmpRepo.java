package repository;
import Entity.PermanentEmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermanentEmpRepo extends JpaRepository<PermanentEmp, Long> {

}