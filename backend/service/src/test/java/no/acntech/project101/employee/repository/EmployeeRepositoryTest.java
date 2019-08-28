package no.acntech.project101.employee.repository;

import no.acntech.project101.company.config.CompanyDatabaseConfig;
import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.config.EmployeeDatabaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@Import({CompanyDatabaseConfig.class, EmployeeDatabaseConfig.class})
@ContextConfiguration(classes = EmployeeRepository.class)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void save() {
//        //TODO: implement, and add necessary annotations for the test to run.
        final Employee employee = new Employee("Piraveen", "Perinparajan", LocalDate.of(1995, 9, 20 ));
        final Employee savedEmployee = employeeRepository.save(employee);
        assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(savedEmployee.getLastName()).isEqualTo(employee.getLastName());
        assertThat(savedEmployee.getDateOfBirth()).isEqualTo(employee.getDateOfBirth());

    }
}