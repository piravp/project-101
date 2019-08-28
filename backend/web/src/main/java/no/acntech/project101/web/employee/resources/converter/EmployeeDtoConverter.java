package no.acntech.project101.web.employee.resources.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import no.acntech.project101.employee.Employee;
import no.acntech.project101.web.employee.resources.EmployeeDto;

@Component
public class EmployeeDtoConverter implements Converter<Employee, EmployeeDto> {

    @Override
    public EmployeeDto convert(final Employee source) {
        return new EmployeeDto(source.getId(), source.getFirstName(), source.getLastName(), source.getDateOfBirth(), source.getCompanyId());
    }
}


// EmployeeDto er det som serveres ut til brukeren (slik kan man filtrere hvilke felt som faktisk vises
// til brukeren. På den måten trenger man ikke vise _alle_ felt i Employee-klassen.