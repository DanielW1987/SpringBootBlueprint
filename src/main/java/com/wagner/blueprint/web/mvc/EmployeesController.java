package com.wagner.blueprint.web.mvc;

import com.wagner.blueprint.config.Endpoints;
import com.wagner.blueprint.config.ViewNames;
import com.wagner.blueprint.service.EmployeeService;
import com.wagner.blueprint.web.dto.EmployeeDto;
import com.wagner.blueprint.web.dto.request.EmployeeRequestDto;
import com.wagner.blueprint.web.dto.response.PaginationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeesController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeesController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(Endpoints.EMPLOYEES_LIST)
  public ModelAndView showEmployeesList() {
    return new ModelAndView(ViewNames.EMPLOYEES_LIST);
  }

  @GetMapping(Endpoints.EMPLOYEES_CREATE)
  public ModelAndView showCreateEmployeeForm() {
    return new ModelAndView(ViewNames.EMPLOYEES_CREATE);
  }

  @GetMapping(Endpoints.EMPLOYEES_EDIT)
  public ModelAndView showEditEmployeeForm(@PathVariable(name = "id") long id) {
    return new ModelAndView(ViewNames.EMPLOYEES_EDIT);
  }
}
