import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/saveAll")
    private void saveAllEmp() {
        employeeService.saveAllEmp();
    }

}