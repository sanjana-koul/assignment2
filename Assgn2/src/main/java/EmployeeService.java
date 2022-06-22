import Entity.Employee;
import Entity.PermanentEmp;
import Entity.TraineeEmp;
import repository.EmployeeRepo;
import repository.PermanentEmpRepo;
import repository.TraineeEmpRepo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Getter
@Setter
public class EmployeeService {

    @Autowired
    private Employee emp;
    @Autowired
    private PermanentEmp permanentEmp;
    @Autowired
    private TraineeEmp traineeEmp;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private TraineeEmpRepo traineeEmpRepo;
    @Autowired
    private PermanentEmpRepo permanentEmpRepo;

    final AtomicReference<List<PermanentEmp>> permEmpsList = new AtomicReference<>();
    final AtomicReference<List<TraineeEmp>> trainEmpsList = new AtomicReference<>();


    @PostConstruct
    private void init() {
        //adding data
        List<PermanentEmp> permanentEmps = new ArrayList<>();
        List<TraineeEmp> traineeEmps = new ArrayList<>();
        for (long i = 1; i <= 100; i++) {
            PermanentEmp permanentEmp = new PermanentEmp();
            permanentEmp.setName("San"+i);
            permanentEmp.setSubject("Maths"+((i%4)+1));
            permanentEmps.add(permanentEmp);

            TraineeEmp traineeEmp = new TraineeEmp();
            traineeEmp.setName("Aks"+i);
            traineeEmp.setSubject("Physics"+((i%4)+1));
            traineeEmps.add(traineeEmp);
        }
        permanentEmpRepo.saveAll(permanentEmps);
        traineeEmpRepo.saveAll(traineeEmps);
    }


    public void saveAllEmp() {
        List<Employee> employees = new ArrayList<>();

        try {
            CompletableFuture.allOf(getPermEmployees(), getTraineeEmployees()).thenRun(() -> {
                permEmpsList.get().forEach(emp -> {
                    Employee e = new Employee();
                    e.setName(emp.getName());
                    e.setSubject(emp.getSubject());
                    employees.add(e);
                });
                trainEmpsList.get().forEach(emp -> {
                    Employee e = new Employee();
                    e.setName(emp.getName());
                    e.setSubject(emp.getSubject());
                    employees.add(e);
                });
                employeeRepo.saveAll(employees);
            }).thenRun(() -> {
                log.info("Saved all the employees");
            });
        } catch (Exception e) {
            log.info("Exception occurred: {}", e.getMessage());
        }
    }

    private CompletableFuture<Void> getPermEmployees() {
        return CompletableFuture.runAsync(() -> {
            permEmpsList.set(permanentEmpRepo.findAll());
            log.info("Found permanent trs");
        }).toCompletableFuture();
    }

    private CompletableFuture<Void> getTraineeEmployees() {
        return CompletableFuture.runAsync(() -> {
            trainEmpsList.set(traineeEmpRepo.findAll());
            log.info("Found temp trs");
        }).toCompletableFuture();
    }
}
