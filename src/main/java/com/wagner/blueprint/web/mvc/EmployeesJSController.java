package com.wagner.blueprint.web.mvc;

import com.wagner.blueprint.config.Endpoints;
import com.wagner.blueprint.config.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeesJSController {

  @GetMapping(Endpoints.EMPLOYEES_JS_LIST)
  public ModelAndView showEmployeesList() {
    return new ModelAndView(ViewNames.EMPLOYEES_JS_LIST);
  }

}
