package com.wagner.blueprint.web.dto;

import com.wagner.blueprint.config.Endpoints;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto extends AbstractEntityDto {

  private long      id;
  private String    firstName;
  private String    lastName;
  private String    email;
  private String    teamName;
  private boolean   active;
  private LocalDate birthday;
  private String    job;
  private String    careerLevel;

  public String getName() {
    return firstName + " " + lastName;
  }

  public String getLevelAndJob() {
    return careerLevel + " " + job;
  }

  public String getEditUrl() {
    return Endpoints.EMPLOYEES_EDIT.replace("{id}", Long.toString(id));
  }

  public String getDeleteUrl() {
    return Endpoints.EMPLOYEES_DELETE.replace("{id}", Long.toString(id));
  }

}
