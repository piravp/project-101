package no.acntech.project101.web.employee.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.service.EmployeeService;
import no.acntech.project101.web.employee.resources.converter.EmployeeConverter;
import no.acntech.project101.web.employee.resources.converter.EmployeeDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employees")
public class EmployeeResource {

    // Variables
    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;
    private final EmployeeConverter employeeConverter;


    //TODO The constructor needs to accept the required dependencies and assign them to class variables
    public EmployeeResource(final EmployeeService employeeService,
                            final EmployeeDtoConverter employeeDtoConverter,
                            final EmployeeConverter employeeConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
        this.employeeConverter = employeeConverter;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        final List<Employee> employees = employeeService.findAll();
        final List<EmployeeDto> collect = employees.stream()
                .map(employeeDtoConverter::convert)
                .collect(Collectors.toList());

          // LEGACY
//        EmployeeDto employee1 = new EmployeeDto(1L, "Piraveen", "Perinparajan", LocalDate.of(1995, 9, 20), 78L );
//        EmployeeDto employee2 = new EmployeeDto(2L, "Ola", "Nordmann", LocalDate.of(1950, 4, 20), 78L );
//        final List<EmployeeDto> collect = new ArrayList<EmployeeDto>();
//        collect.add(employee1);
//        collect.add(employee2);
        return ResponseEntity.ok(collect);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {

        final Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()){
            final EmployeeDto convert = employeeDtoConverter.convert(employee.get());
            return ResponseEntity.ok(convert);
        } else {
            return ResponseEntity.notFound().build();
        }

        // LEGACY
//        EmployeeDto employee2 = new EmployeeDto(id, "Ola", "Nordmann", LocalDate.of(1950, 4, 20), 78L );
//        return ResponseEntity.ok(employee2);
    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody final EmployeeDto employeeDto) {
        final Employee convert = employeeConverter.convert(employeeDto);
        final Employee saved = employeeService.save(convert);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable final Long id) {
        final Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()) {
            employeeService.delete(id);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }

        // LEGACY
//        return ResponseEntity.accepted().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity updateEmployee(@PathVariable final Long id, @RequestBody final EmployeeDto employeeDto) {
        final Optional<Employee> optionalEmployee = employeeService.findById(id);

        if(optionalEmployee.isPresent()){
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setDateOfBirth(employeeDto.getDateOfBirth());
            existingEmployee.setCompanyId(employeeDto.getCompanyId());

            Employee saved = employeeService.save(existingEmployee);

            final EmployeeDto convert = employeeDtoConverter.convert(saved);
            return ResponseEntity.ok(convert);
        } else {
            return ResponseEntity.notFound().build();
        }

        // LEGACY
//        EmployeeDto employee = new EmployeeDto(id, "Ola", "Nordmann", LocalDate.of(1950, 4, 20), 78L );
//
//        return ResponseEntity.ok(employee);
    }
}
