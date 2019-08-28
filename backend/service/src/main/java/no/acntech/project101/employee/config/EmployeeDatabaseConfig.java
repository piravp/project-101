package no.acntech.project101.employee.config;

//TODO add configuration for repository to enable the jpa repository created

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "no.acntech.project101.employee.repository")
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = "no.acntech.project101.employee")
public class EmployeeDatabaseConfig {

}
