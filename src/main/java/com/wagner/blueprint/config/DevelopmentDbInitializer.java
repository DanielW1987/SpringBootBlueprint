package com.wagner.blueprint.config;

import com.wagner.blueprint.model.entity.Employee;
import com.wagner.blueprint.model.repository.EmployeeRepository;
import com.wagner.blueprint.model.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile({"dev", "integration-test"})
public class DevelopmentDbInitializer implements ApplicationRunner {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public DevelopmentDbInitializer(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public void run(ApplicationArguments applicationArguments) {
    createTestData();
  }

  private void createTestData() {
   Employee employee1 = Employee.builder()
      .firstName("John")
      .lastName("Doe")
      .email("john.doe@example.com")
      .teamName("Team Backend")
      .birthday(LocalDate.of(1970, 1, 1))
      .active(true)
      .job("Java Developer")
      .careerLevel("Senior")
      .build();

    Employee employee2 = Employee.builder()
      .firstName("Owen")
      .lastName("Hunt")
      .email("owen.hunt@example.com")
      .teamName("Team Frontend")
      .birthday(LocalDate.of(1992, 10, 26))
      .active(true)
      .job("Java Developer")
      .careerLevel("Junior")
      .build();

    Employee employee3 = Employee.builder()
      .firstName("Elisa")
      .lastName("Baily")
      .email("elisa.baily@example.com")
      .teamName("Team Backend")
      .birthday(LocalDate.of(1965, 7, 5))
      .active(true)
      .job("Product Manager")
      .careerLevel("Experienced Senior")
      .build();

    Employee employee4 = Employee.builder()
      .firstName("Tom")
      .lastName("Hart")
      .email("tom.hart@example.com")
      .teamName("Team DevOps")
      .birthday(LocalDate.of(1987, 9, 15))
      .active(false)
      .job("QA Engineer")
      .careerLevel("Professional")
      .build();

    Employee employee5 = Employee.builder()
      .firstName("Miranda")
      .lastName("Grey")
      .email("miranda.grey@example.com")
      .teamName("Team Frontend")
      .birthday(LocalDate.of(1982, 1, 15))
      .active(true)
      .job("Java Developer")
      .careerLevel("Senior")
      .build();

    employeeRepository.save(employee1);
    employeeRepository.save(employee2);
    employeeRepository.save(employee3);
    employeeRepository.save(employee4);
    employeeRepository.save(employee5);
  }
}
