package no.acntech.project101.web.employee.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import no.acntech.project101.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employees")
//TODO This is a REST controler and should receive request on path employees
public class EmployeeResource {

    // Global variables
    final List<EmployeeDto> collect = new ArrayList<EmployeeDto>();

    //TODO The constructor needs to accept the required dependencies and assign them to class variables
    public EmployeeResource() {
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        EmployeeDto employee1 = new EmployeeDto(1L, "Piraveen", "Perinparajan", LocalDate.of(1995, 9, 20), 78L );
        EmployeeDto employee2 = new EmployeeDto(2L, "Ola", "Nordmann", LocalDate.of(1950, 4, 20), 78L );

        collect.add(employee1);
        collect.add(employee2);
        return ResponseEntity.ok(collect);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {

//        for (EmployeeDto employeeDto : collect){
//            if (employeeDto.getId().equals(id)){
//                // Found it
//                return ResponseEntity.ok(employeeDto);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        }
        EmployeeDto employee2 = new EmployeeDto(id, "Ola", "Nordmann", LocalDate.of(1950, 4, 20), 78L );


        return ResponseEntity.ok(employee2);
    }

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody final EmployeeDto employeeDto) {
        //TODO Create a POST endpoint that accepts an employeeDTO and saves it in the database
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(55L)
                .toUri();
        return ResponseEntity.created(uri).build();
    }


    public ResponseEntity deleteEmployee() {
        // TODO Create a DELETE endpoint that deletes a specific employee
        return null;
    }

    public ResponseEntity updateEmployee() {
        //TODO Create a PATCH endpoint that updates an employee with new values
        return null;
    }
}
