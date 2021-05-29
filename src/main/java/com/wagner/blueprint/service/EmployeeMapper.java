package com.wagner.blueprint.service;

import com.wagner.blueprint.model.entity.Employee;
import com.wagner.blueprint.web.dto.EmployeeDto;
import com.wagner.blueprint.web.dto.request.EmployeeRequestDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

  public Employee map(EmployeeRequestDto request) {
    Employee employee = Employee.builder()
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .email(request.getEmail())
      .birthday(request.getBirthday())
      .careerLevel(request.getCareerLevel())
      .job(request.getJob())
      .active(true)
      .build();

    return employee;
  }

  public EmployeeDto map(Employee employee) {
    return EmployeeDto.builder()
      .id(employee.getId())
      .firstName(employee.getFirstName())
      .lastName(employee.getLastName())
      .email(employee.getEmail())
      .birthday(employee.getBirthday())
      .careerLevel(employee.getCareerLevel())
      .job(employee.getJob())
      .teamName(employee.getTeamName())
      .active(employee.isActive())
      .build();
  }
}
