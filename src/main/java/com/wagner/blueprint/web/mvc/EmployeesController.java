package com.wagner.blueprint.web.mvc;

import com.wagner.blueprint.config.Endpoints;
import com.wagner.blueprint.config.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeesController {

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
